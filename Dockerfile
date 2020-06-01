FROM python:3

RUN pip install spotipy
RUN python -m easy_install pymongo
RUN pip install python-dotenv

ENV SPOTIPY_CLIENT_ID=7ade872bdb9e43dbb376145736fe2384
ENV SPOTIPY_CLIENT_SECRET=5e237a70b7894141b87e1c73a9c5c8d4

ADD script.py /
ADD .env /

#docker network create patagonian_net
#docker build -t python-example . && docker run --network=patagonian_net python-example -a 6ZZ2DeepA3GpoGU4KwqSlU

ENTRYPOINT [ "python", "./script.py" ]
