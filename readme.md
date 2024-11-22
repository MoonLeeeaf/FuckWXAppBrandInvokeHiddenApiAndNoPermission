## FuckWXAppBrandInvokeHiddenApiAndNoPermission

### What's this?

See:

```
com.tencent.mm: Accessing hidden field Landroid/view/inputmethod/InputMethodManager;->mServedView:Landroid/view/View; (blocked, reflection, denied)
com.tencent.mm: Accessing hidden field Landroid/view/inputmethod/InputMethodManager;->mNextServedView:Landroid/view/View; (blocked, reflection, denied)
WifiService: Permission violation - getConfiguredNetworks not allowed for uid=10216, packageName=com.tencent.mm, reason=java.lang.SecurityException: Location mode is disabled for the device
WifiService: Permission violation - getScanResults not allowed for uid=10216, packageName=com.tencent.mm, reason=java.lang.SecurityException: Location mode is disabled for the device
```

When I used wechat appbrand, it crashed at some pages. It made me feel angry because I couldn't use it normally😡

So I made this module to solve this problem☝️🤓

### Supported version

Maybe any versions of wechat

Tested with 7.0.9 (Not Google Play version)
