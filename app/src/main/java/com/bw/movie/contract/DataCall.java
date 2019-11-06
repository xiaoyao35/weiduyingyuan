package com.bw.movie.contract;
import com.bw.movie.model.Result;

/**
 * author: 韩聪聪
 * data: 2019/11/5 09:9:44
 * function:
 */
public interface DataCall<T> {
    void success(T data);
    void fail(Result result);
}
