//
//  JDPAuthObject.h
//  JDPAuthSDK
//
//  Created by dulinlin on 16/11/2.
//  Copyright © 2016年 dulinlin. All rights reserved.
//

#ifndef JDPAuthObject_h
#define JDPAuthObject_h




/*!  @breif 错误码
 *
 *  0001  :订单不存在或者订单查询异常
 *  0002  :订单已完成
 *  0003  :订单已取消
 *  0004  :商户信息不匹配
 *  0005  :获取不到用户pin
 *  0006  :参数异常
 *  0007  :获取不到商户秘钥
 *  0008  :验签失败
 *  CASH000000  :商户收单异常
 *  CASH000001  :系统请求失败
 *  CASH000002  :支付异常
 *  CASH000003  :登录超时
 *  CASH000004  :支付验签失败
 *  CASH000005  :支付金额异常
 *  CASH000006  :商户解密异常
 *  CASH000007  :校验商户签名异常
 *  CASH000008  :获取短信验证码失败
 *  CASH000009  :此卡不支持在线支付
 *  CASH000010  :用户卡ID出错
 *  CASH000011  :生日输入错误
 *  CASH000012  :验证码校验失败
 *  CASH000013  :生日校验次数超过6次
 *  CASH000014  :商户提交参数异常
 *  CASH000015  :扫码支付临时订单信息获取异常
 *  CASH000018  :该商户信息不存在
 *  CASH000019  :该商户DES秘钥不存在
 *  CASH000024  :商户提交参数异常
 *  -1009       :无网
 *  -1001       :请求超时
 */

/**
 *  支付状态
 */
#define JDP_PAY_AUTH_SUCCESS  @"JDP_PAY_SUCCESS"
#define JDP_PAY_AUTH_FAIL  @"JDP_PAY_FAIL"
#define JDP_PAY_AUTH_CANCEL  @"JDP_PAY_CANCEL"
#define JDP_PAY_AUTH_NONE  @"JDP_PAY_NONE"   //暂无使用



/**
 *  支付完成回调
 *
 *  @param resultDict 支付结果数据  其中包含字段:
 *                                          errorCode:错误码
 *                                          payStatus:支付状态
 *                                          extraData:成功返回数据 (NSDictionary对象)
 */
typedef void (^JDPAuthCompletionBlock)(NSDictionary *resultDict);





#endif /* JDPAuthObject_h */
