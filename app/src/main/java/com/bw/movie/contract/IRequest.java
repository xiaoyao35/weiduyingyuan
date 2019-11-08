package com.bw.movie.contract;

import com.bw.movie.model.Banner;
import com.bw.movie.model.ReYing;
import com.bw.movie.model.Result;
import com.bw.movie.model.ShangYing;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author: 韩聪聪
 * data: 2019/11/5 09:9:44
 * function:
 */
public interface IRequest {
      //展示Xbanner
      @GET("tool/v2/banner")
      Observable<Result<List<Banner>>> Xbanner();
      //展示热映数据
      @GET("movie/v2/findHotMovieList")
      Observable<Result<List<ReYing>>> findHotMovieList(@Query("page")int page,
                                                        @Query("count")int count);
      //展示即将上映数据
      @GET("movie/v2/findComingSoonMovieList")
      Observable<Result<List<ShangYing>>> findComingSoonMovieList(@Query("page")int page,
                                                                  @Query("count")int count);
      //展示热门数据
      @GET("movie/v2/findHotMovieList")
      Observable<Result<List<ReYing>>> remenMovieList(@Query("page")int page,
                                                        @Query("count")int count);
}
