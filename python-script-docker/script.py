import argparse
import logging

from dotenv import load_dotenv
load_dotenv()
import os

from spotipy.oauth2 import SpotifyClientCredentials
import spotipy

from pymongo import MongoClient
import pymongo
import json

logger = logging.getLogger('SCRIPT')
logging.basicConfig(level='INFO')

client_credentials_manager = SpotifyClientCredentials()
sp = spotipy.Spotify(client_credentials_manager=client_credentials_manager)

def get_args():
  parser = argparse.ArgumentParser(description='Persist all tracks from artists')
  parser.add_argument('-a', '--artists', nargs="+", required=True, help='Artists IDs separated by commas')
  return parser.parse_args()

def find_artist_tracks(artist_id):
  albums = find_artist_albums(artist_id)
  tracks = []
  for album in albums:
    tracks_by_album = find_artist_tracks_by_album(album)
    tracks.extend(tracks_by_album)
  return tracks

def find_artist_albums(artist_id):
  albums = []
  results = sp.artist_albums(artist_id, album_type='album')
  albums.extend(results['items'])
  while results['next']:
    results = sp.next(results)
    albums.extend(results['items'])
  return albums

def find_artist_tracks_by_album(album):
  tracks = []
  results = sp.album_tracks(album['id'])
  tracks.extend(results['items'])
  while results['next']:
    results = sp.next(results)
    tracks.extend(results['items'])
  return tracks

def persist_tracks(tracks):
  client = MongoClient(
    os.environ.get("MONGO_HOST"),
    username=os.environ.get("MONGO_USERNAME"),
    password=os.environ.get("MONGO_PASSWORD"),
    authSource=os.environ.get("MONGO_AUTHSOURCE")
  )
  db = client.get_database('patagonian')
  for track in tracks:
    trackJson = json.loads(json.dumps(track))
    db.songs.find_one_and_update({"id": track['id']}, {"$set": trackJson}, upsert=True)

def main():
  args = get_args()
  artists = args.artists
  for artist in artists:
    tracks = find_artist_tracks(artist)
    logger.info(" " + str(len(tracks)) + " " + tracks[0]['artists'][0]['name'] + " songs found")
    persist_tracks(tracks)

if __name__ == '__main__':
  main()