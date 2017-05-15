/********* JDPay.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import "JDPAuthSDK.h"
#import "JDPAuthObject.h"

@interface JDPay : CDVPlugin {
    NSString *app_id;
    NSString *merchant;
    NSString *callbackId;
}

- (void)payment:(CDVInvokedUrlCommand*)command;

@end

@implementation JDPay

- (void)pluginInitialize {
    app_id = [[self.commandDelegate settings] objectForKey:@"jdpappid"];
    merchant = [[self.commandDelegate settings] objectForKey:@"jdpmerchant"];
    [[JDPAuthSDK sharedJDPay] registServiceWithAppID:app_id merchantID:merchant];
}

- (void)payment:(CDVInvokedUrlCommand*)command
{
    NSDictionary *params = [command.arguments objectAtIndex:0];
    callbackId = command.callbackId;

    if (params == nil) {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"京东支付" message:@"参数格式不正确" delegate:nil cancelButtonTitle:(NSString *)@"知道了" otherButtonTitles:nil];
        [alert show];
    } else {

        NSString *orderId = [params objectForKey:@"orderId"];
        NSString *signData = [params objectForKey:@"signData"];

        [[JDPAuthSDK sharedJDPay] payWithViewController:self.viewController orderId:orderId signData:signData completion:^(NSDictionary *resultDict) {

            CDVPluginResult *pluginResult;
            NSString *status = [resultDict objectForKey:@"payStatus"];

            if ([status isEqualToString:@"JDP_PAY_SUCCESS"]) {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
            } else{
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsDictionary:resultDict];
            }

            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }];
    }
}

- (void)handleOpenURL:(NSNotification *)notification {
    NSURL* url = [notification object];
    if ([url isKindOfClass:[NSURL class]] && [url.scheme isEqualToString:[NSString stringWithFormat:@"jdpauth%@", app_id]])
    {
        [[JDPAuthSDK sharedJDPay] handleOpenURL:url];
    }
}

@end
