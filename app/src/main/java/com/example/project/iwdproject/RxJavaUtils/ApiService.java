package com.example.project.iwdproject.RxJavaUtils;




import com.example.project.iwdproject.Beans.ActiveUsdtBean;
import com.example.project.iwdproject.Beans.BalanceBean;
import com.example.project.iwdproject.Beans.BalanceTwoBean;
import com.example.project.iwdproject.Beans.CodeBean;
import com.example.project.iwdproject.Beans.DrawalBean;
import com.example.project.iwdproject.Beans.EmailBean;
import com.example.project.iwdproject.Beans.EtcInfoBean;
import com.example.project.iwdproject.Beans.ExchangeBean;
import com.example.project.iwdproject.Beans.FeesBean;
import com.example.project.iwdproject.Beans.FirstHomePageBean;
import com.example.project.iwdproject.Beans.FlashBean;
import com.example.project.iwdproject.Beans.InvitationCodeBean;
import com.example.project.iwdproject.Beans.LeaseBean;
import com.example.project.iwdproject.Beans.LeaseDetailBean;
import com.example.project.iwdproject.Beans.LeaseStopBean;
import com.example.project.iwdproject.Beans.LoginBean;
import com.example.project.iwdproject.Beans.MarketBean;
import com.example.project.iwdproject.Beans.ModifyPassWordBean;
import com.example.project.iwdproject.Beans.MyBalanceListBean;
import com.example.project.iwdproject.Beans.MyProfitBean;
import com.example.project.iwdproject.Beans.MyProfitBeanLogBean;
import com.example.project.iwdproject.Beans.PayPasswordBean;
import com.example.project.iwdproject.Beans.ProfitLogBean;
import com.example.project.iwdproject.Beans.RecordBean;
import com.example.project.iwdproject.Beans.RegisterBean;
import com.example.project.iwdproject.Beans.TeamBean;
import com.example.project.iwdproject.Beans.UpDataPassBean;
import com.example.project.iwdproject.Beans.UsdtBalanceBean;
import com.example.project.iwdproject.Beans.ZhuanBean;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {

//    final String BASE_URL = "http://192.168.0.254:8080/chat-face/";

    final String BASE_URL = "http://47.244.218.212/api/";
    final String SHARE_BASEURL ="http://103.197.71.92:8080";
//     String Base64 = StoreUtil.getBase64();

//    http://www.eo.com/server/index.php?g=Web&c=Mock&o=simple&projectID=15&uri=/users/help_list


//    http://192.168.252.111:88/index.php?app=api&mod=User&act=show



    /**
     * 手机号登陆
     * @return
     */
//    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> getLoginInterface(@Query("phone") String phone,
                                            @Query("password") String password,
                                            @Header("Content-Type") String header);


    /**
     * 邮箱登陆
     * @return
     */
//    @FormUrlEncoded
    @POST("login_email")
    Observable<EmailBean> getLoginEmail(@Query("email") String email,
                                        @Query("password") String password,
                                        @Header("Content-Type") String header);


    /**
     * 修改忘记密码
     * @return
     */
//    @FormUrlEncoded
    @POST("update_password")
    Observable<ModifyPassWordBean> getModifyPass(@Query("phone") String phone,
                                                 @Query("password") String password,
                                                 @Query("code") String code,
                                                 @Header("Content-Type") String header);


    /**
     * 手机号获取验证码
     * @return
     */
//    @FormUrlEncoded
    @POST("code")
    Observable<CodeBean> getVerificationCode(@Query("phone") String phone,
                                             @Header("Content-Type") String header);



    /**
     * 邮箱获取验证码
     * @return
     */
//    @FormUrlEncoded
    @POST("email")
    Observable<CodeBean> getEailCode(@Query("email") String email,
                                             @Header("Content-Type") String header);



    /**
     * 首页banner和滚动通告
     * @return
     */
//    @FormUrlEncoded
    @POST("home")
    Observable<FirstHomePageBean> getFirstHome(@Header("Content-Type") String header);


    /**
     * 获取邀请码
     * @return
     */
//    @FormUrlEncoded
    @POST("invitation")
    Observable<InvitationCodeBean> getInvitationCode(@Query("id") int id,
           @Header("Authorization") String Authorization ,@Header("Content-Type") String header);





    /**
     * 获取租赁详情
     * @return
     */
//    @FormUrlEncoded
    @POST("lease_detail")
    Observable<LeaseDetailBean> getLeaseDetail(@Query("id") int id,
                                                  @Header("Authorization") String Authorization , @Header("Content-Type") String header);



    /**
     * 获取我的总资产
     * @return
     */
//    @FormUrlEncoded
    @POST("my_iwd_balance")
    Observable<BalanceBean> getMybalance(@Header("Authorization") String Authorization ,
                                         @Header("Content-Type") String header);




    /**
     * 获取我的USTD总资产
     * @return
     */
//    @FormUrlEncoded
    @POST("my_usdt_balance")
    Observable<UsdtBalanceBean> getMyUstdbalance(@Header("Authorization") String Authorization ,
                                                 @Header("Content-Type") String header);




    /**
     * 获取可用资产
     * @return
     */
//    @FormUrlEncoded
    @POST("my_active_usdt")
    Observable<ActiveUsdtBean> getActiveUsdt(@Header("Authorization") String Authorization ,
                                            @Header("Content-Type") String header);




    /**
     * 手机号转账
     * @return
     */
//    @FormUrlEncoded
    @POST("transfer")
    Observable<ZhuanBean> getTransfer(@Query("id") int id,@Query("num") String num,@Query("phone") String phone, @Header("Authorization") String Authorization,
                                        @Header("Content-Type") String header);


    /**
     * 邮箱号转账
     * @return
     */
//    @FormUrlEncoded
    @POST("transfer")
    Observable<ZhuanBean> getEmailTransfer(@Query("id") int id,@Query("num") String num,@Query("email") String email, @Header("Authorization") String Authorization,
                                      @Header("Content-Type") String header);






    /**
     * 获取我的租赁资产
     * @return
     */
//    @FormUrlEncoded
    @POST("my_balance2")
    Observable<BalanceTwoBean> getBalanceTwo(@Query("id") int id,@Header("Authorization") String Authorization,
                                            @Header("Content-Type") String header);





    /**
     * 租赁
     * @return
     */
//    @FormUrlEncoded
    @POST("lease")
    Observable<LeaseBean> getLease(@Query("num") String  num, @Query("token_id") int token_id,@Header("Authorization") String Authorization,
                                   @Header("Content-Type") String header);


    /**
     * 获取我的租赁资产记录
     * @return
     */
//    @FormUrlEncoded
    @POST("my_profit_log2")
    Observable<MyProfitBeanLogBean> getMyProfitBeanLog(@Query("id") int id, @Header("Authorization") String Authorization,
                                                  @Header("Content-Type") String header);



    /**
     * 取消租赁
     * @return
     */
//    @FormUrlEncoded
    @POST("lease_stop")
    Observable<LeaseStopBean> getLeaseStop(@Query("id") int id, @Header("Authorization") String Authorization,
                                                 @Header("Content-Type") String header);


    /**
     * 我的收益
     * @return
     */
//    @FormUrlEncoded
    @POST("my_profit")
    Observable<MyProfitBean> getMyProfit(@Header("Authorization") String Authorization ,
                                          @Header("Content-Type") String header);




    /**
     * 计算手续费
     * @return
     */
//    @FormUrlEncoded
    @POST("charge")
    Observable<FeesBean> getFees(@Query("id") int id,@Query("num") String num,@Header("Authorization") String Authorization ,
                                 @Header("Content-Type") String header);





    /**
     * 修改和设置支付密码
     * @return
     */
//    @FormUrlEncoded
    @POST("update_pay_password")
    Observable<PayPasswordBean> getPayPassword(@Query("pay_password") String pay_password, @Query("password") String password, @Header("Authorization") String Authorization ,
                                        @Header("Content-Type") String header);




    /**
     * 修改登录密码
     * @return
     */
//    @FormUrlEncoded
    @POST("update_password2")
    Observable<UpDataPassBean> getUpDataPass(@Query("new_password") String new_password, @Query("password") String password, @Header("Authorization") String Authorization ,
                                              @Header("Content-Type") String header);






    /**
     * 1:充值 2:USDT收益 3:IWD收益 4:提现 5:邀请奖励 6: 7:场内交易 8:租赁 9解除租赁
     * @return
     */
//    @FormUrlEncoded
    @POST("my_log")
    Observable<RecordBean> getRecord(@Query("id") int id, @Header("Authorization") String Authorization ,
                                         @Header("Content-Type") String header);




    /**
     * 提现
     * @return
     */
//    @FormUrlEncoded
    @POST("withdrawal")
    Observable<DrawalBean> getWithdrawal(@Query("id") int id, @Query("num") String num, @Query("address") String address, @Header("Authorization") String Authorization ,
                                   @Header("Content-Type") String header);




    /**
     * 兑换计算
     * @return
     */
//    @FormUrlEncoded
    @POST("exchange_calculation")
    Observable<FlashBean> getFlash(@Query("id") int id, @Query("num") String num, @Header("Authorization") String Authorization ,
                                        @Header("Content-Type") String header);




    /**
     * 确认兑换
     * @return
     */
//    @FormUrlEncoded
    @POST("exchange")
    Observable<ExchangeBean> getExchange(@Query("id") int id, @Query("num") String num, @Header("Authorization") String Authorization ,
                                      @Header("Content-Type") String header);





    /**
     * 获取我的资产列表
fv     */
//    @FormUrlEncoded
    @POST("my_balance")
    Observable<MyBalanceListBean> getBalanceList(@Header("Authorization") String Authorization , @Header("Content-Type") String header);




    /**
     * 我的收益记录
     */
//    @FormUrlEncoded
    @POST("my_profit_log")
    Observable<ProfitLogBean> getProfitLog(@Header("Authorization") String Authorization , @Header("Content-Type") String header);






    /**
     * 团队人数
     */
//    @FormUrlEncoded
    @POST("my_team")
    Observable<TeamBean> getMyTeam(@Header("Authorization") String Authorization , @Header("Content-Type") String header);





    /**
     * 充值页面
     * @return
     */
//    @FormUrlEncoded
    @POST("eth_info")
    Observable<EtcInfoBean> getEtcInfo(@Header("Authorization") String Authorization , @Header("Content-Type") String header);





    /**
     * 币行情取前三
     * @return
     */
//    @FormUrlEncoded
    @POST("token")
    Observable<MarketBean> getMarket(@Header("Content-Type") String header);

//
//    /**
//     * 验证验证码
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("user/checkCode")
//    Observable<CheckCodeBean> getCheckCode(@Query("phone") String phone,
//                                           @Query("code") String code,
//                                           @Header("Content-Type") String header);
//

    /**
     * 手机号注册账号
     * @return
     */
//    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> getRegisterCode(@Query("phone") String phone,
                                             @Query("password") String password,
                                             @Query("code") String code,
                                             @Query("invitation_num") String invitation_num,
                                             @Header("Content-Type") String header);



    /**
     * 邮箱注册账号
     * @return
     */
//    @FormUrlEncoded
    @POST("register_email")
    Observable<RegisterBean> getRegisterEmail(@Query("email") String email,
                                             @Query("password") String password,
                                             @Query("code") String code,
                                             @Query("invitation_num") String invitation_num,
                                             @Header("Content-Type") String header);
//
//
//    /**
//     * 修改密码
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("user/updatePassword")
//    Observable<CheckCodeBean> getupdatePassword(@Query("phone") String phone,
//                                                @Query("newPassword") String newPassword,
//                                                @Query("oldPassword") String oldPassword,
//                                                @Header("Content-Type") String header);
//
//
//
//    /**
//     * 风险量表
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("qa/getTitleOfPlan")
//    Observable<PlanBean> getRiskData(@Query("userId") Integer userId,
//                                     @Header("Content-Type") String header);
//
//
//
//    /**
//     * 问答题
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("qa/getQByTitle")
//    Observable<QByTitleBean> getQByTitleData(@Query("titleId") Integer titleId,
//                                             @Header("Content-Type") String header);
//
//
//    /**
//     * 问答题提交
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("qa/createAnswer")
//    Observable<AnswerBean> getAnswerData(@Query("userId") Integer userId,
//                                         @Query("answer") String answer,
//                                         @Header("Content-Type") String header);
//
//
//    /**
//     * 测试类目
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/getTitleOfPlan")
//    Observable<SubhumanBean> getSubhumanData(@Query("userId") Integer userId,
//                                             @Header("Content-Type") String header);
//
//
//
//    /**
//     * 测试类目
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/getByTitleId")
//    Observable<ByTitleBean> getByTitle(@Query("userId") Integer userId,
//                                       @Query("titleId") Integer titleId,
//                                       @Header("Content-Type") String header);
//
//
//    /**
//     * 形态测试
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/stTest")
//    Observable<Health> getStTest(@Query("userId") Integer userId,
//                                 @Query("health") String health,
//                                 @Query("weight") String weight,
//                                 @Header("Content-Type") String header);
//
//
//    /**
//     * 形态测试
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/xfTj")
//    Observable<FTJBean> getXFTJ(@Query("userId") Integer userId,
//                                @Query("score") String score,
//                                @Header("Content-Type") String header);
//
//
//
//
//    /**
//     * 耐力测试：仰卧起坐（女）
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/nlSitUpTest")
//    Observable<FTJBean> getNlSitUp(@Query("userId") Integer userId,
//                                   @Query("num") String num,
//                                   @Header("Content-Type") String header);
//
//
//
//    /**
//     * 耐力测试：俯卧撑（男）
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/nlPushUpTest")
//    Observable<FTJBean> getNlPushUp(@Query("userId") Integer userId,
//                                    @Query("num") String num,
//                                    @Header("Content-Type") String header);
//
//
//    /**
//     *柔韧测试
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/rrTest")
//    Observable<FTJBean> getRrTest(@Query("userId") Integer userId,
//                                  @Query("cm") String cm,
//                                  @Header("Content-Type") String header);
//
//
//    /**
//     *肌肉能力测试
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/llTest")
//    Observable<FTJBean> getJIRouTest(@Query("userId") Integer userId,
//                                     @Query("kg") String kg,
//                                     @Header("Content-Type") String header);
//
//
//
//    /**
//     *平衡测试
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/phTest")
//    Observable<FTJBean> getPhTest(@Query("userId") Integer userId,
//                                  @Query("second") String second,
//                                  @Header("Content-Type") String header);
//
//
//    /**
//     *平衡测试
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("user/updateNickname")
//    Observable<ChageNameBean> getChangeUserName(@Query("id") Integer id,
//                                                @Query("nickname") String nickname,
//                                                @Header("Content-Type") String header);
//
//
//
//    /**
//     *测试记录
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/getPlan")
//    Observable<CeShiPlanBean> getCeShiPlan(@Query("userId") Integer userId, @Header("Content-Type") String header);
//
//
//    /**
//     *问答记录
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("qa/getPlan")
//    Observable<ActiveBean> getQaPlan(@Query("userId") Integer userId, @Header("Content-Type") String header);
//
//
//
//    /**
//     *运动指导
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/guidance")
//    Observable<RunBean> getRun(@Query("userId") Integer userId, @Header("Content-Type") String header);
//
//
//    /**
//     *运动指导
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/matte")
//    Observable<OverBean> getMatte(@Header("Content-Type") String header);
//
//
//    /**
//     *运动指导
//     * @return
//     */
////    @FormUrlEncoded
//    @POST("testTitle/xfRunMeterTest")
//    Observable<FTJBean> getRunMeterTest(
//            @Query("userId") Integer userId,
//            @Query("xfRunMeter") String xfRunMeter,
//            @Header("Content-Type") String header);




    /**
     * 视频列表
     * @return
     */
//    @FormUrlEncoded
//    @POST("index.php?app=api&mod=Album&act=getCatalog")
//    Observable<VideoListBean> getVideoListData(@Field("id") int phoneNumber, @Field("uid") int userId);


    /**
     * 视频列表
     * @return
     */
//    @FormUrlEncoded
//    @POST("index.php?app=api&mod=Video&act=videoInfo")
//    Observable<VIdeoPalyerBean> getVideoData(@Field("id") String id);


    /**
     * 根据id 获得资料
     * @return
     */
//    @FormUrlEncoded
//    @POST("index.php?app=api&mod=User&act=show")
//    Observable<MySelfBean> getSelfData(@Field("uid") int id);



    /**
     * 头像上传
     * @return
     */
//    @Multipart
//    @POST("index.php?app=api&mod=User&act=upload_face")
//    Observable<HeadImageBean> upLoadHeadImage(@Header("uid") int uid, @Part MultipartBody.Part uploadFile);



}
