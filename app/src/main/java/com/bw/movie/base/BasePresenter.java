package com.bw.movie.base;

import com.bw.movie.contract.DataCall;
import com.bw.movie.contract.IRequest;
import com.bw.movie.model.Result;
import com.bw.movie.utils.HttpUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: 韩聪聪
 * data: 2019/11/5 09:9:55
 * function:
 */
public abstract class BasePresenter {
    private DataCall dataCall;

    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    //创建方法
    public void requestData(Object...args){
        IRequest iRequest = HttpUtil.getInstance().create(IRequest.class);
        getModel(iRequest,args).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                          if (dataCall==null){
                              return;
                          }
                         /* if (HttpUtil.getInstance().getWangLuoPanduan(App.context)){*/
                              if (result.status.equals("0000")){
                                  dataCall.success(result.result);
                                  //提示信息
                                  dataCall.fail(result);
                              }else{
                                  dataCall.fail(result);
                              }
                         /* }else{
                              Toast.makeText(App.context, "无网络", Toast.LENGTH_SHORT).show();
                          }*/
                         
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                          if (dataCall==null){
                              return;
                          }
                          throwable.printStackTrace();
                          dataCall.fail(new Result(throwable.getMessage()));
                    }
                });
    }
    //创建方法
    protected abstract Observable getModel(IRequest iRequest,Object...args);
    //创建防止内存泄漏的方法
    public void destroy(){
        dataCall=null;
    }
}
