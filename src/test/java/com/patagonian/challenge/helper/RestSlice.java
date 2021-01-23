package com.patagonian.challenge.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

public class RestSlice<T> implements Slice<T> {
  private SliceImpl<T> pageDelegate = new SliceImpl<T>(new ArrayList<>(0));

  public List<T> getContent() {
    return pageDelegate.getContent();
  }

  public int getNumber() {
    return pageDelegate.getNumber();
  }

  public int getNumberOfElements() {
    return pageDelegate.getNumberOfElements();
  }

  public int getSize() {
    return pageDelegate.getSize();
  }

  public Sort getSort() {
    return pageDelegate.getSort();
  }

  public boolean hasContent() {
    return pageDelegate.hasContent();
  }

  public boolean hasNext() {
    return pageDelegate.hasNext();
  }

  public boolean hasPrevious() {
    return pageDelegate.hasPrevious();
  }

  public boolean isFirst() {
    return pageDelegate.isFirst();
  }

  public boolean isLast() {
    return pageDelegate.isLast();
  }

  public Iterator<T> iterator() {
    return pageDelegate.iterator();
  }

  public <S> Slice<S> map(Function<? super T, ? extends S> converter) {
    return pageDelegate.map(converter);
  }

  public Pageable nextPageable() {
    return pageDelegate.nextPageable();
  }

  public Pageable previousPageable() {
    return pageDelegate.previousPageable();
  }

  public void setContent(List<T> content) {
    pageDelegate = new SliceImpl<>(content);
  }

  public String toString() {
    return pageDelegate.toString();
  }
}
