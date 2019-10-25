2019-03-06 王康

### 1.客户端调用（Client Credential）使用说明
  关键值grant_type=client_credentials
```http request
http://localhost:8020/oauth/token?grant_type=client_credentials&scope=select&client_id=first&client_secret=123456
```
### 返回数据(json)

```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU1MTg2OTcwMiwiYXV0aG9yaXRpZXMiOlsib2F1dGgyIl0sImp0aSI6IjQ5NjU3OGNiLTRhN2UtNDA1NC05ODVkLWNiY2JmMTJkMjEyYiIsInVybCI6Imh0dHA6Ly93d3cuemhlbmd0b25naXQuY29tLyIsImNsaWVudF9pZCI6ImdhdGVfd2F5In0.bGYzjFQDHsZDQV0CcFzBuxK80mInvvOhkev1HMpR1Z0",
    "token_type": "bearer",
    "expires_in": 7200,
    "scope": "select",
    "url": "http://www.zhengtongit.com/",
    "jti": "496578cb-4a7e-4054-985d-cbcbf12d212b"
}
```


### 2.用户名密码模式 (Resource Owner Password Credential)使用说明
  关键值grant_type=password
```http request
http://localhost:8020/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=center&client_secret=123456
```
### 返回数据(json)

```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoidXNlcl8xIiwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU1MTg3MDg4OCwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiJjZDIwZDQzNi0wNjFjLTRkMTMtYTQ2OS0zNDNmNTQzOWUwMWIiLCJ1cmwiOiJodHRwOi8vd3d3LnpoZW5ndG9uZ2l0LmNvbS8iLCJjbGllbnRfaWQiOiJjZW50ZXIifQ.d_ex8ui6gsz2YfTaTdiuu2YUWXZRtSl9b2sMOfosq84",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoidXNlcl8xIiwic2NvcGUiOlsic2VsZWN0Il0sImF0aSI6ImNkMjBkNDM2LTA2MWMtNGQxMy1hNDY5LTM0M2Y1NDM5ZTAxYiIsImV4cCI6MTU1MjEyMjM5MCwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiI3ZGFkZGE2Yi04OTc0LTRjMjUtYjZmYi02YzQyNTkzYmM2ZTUiLCJ1cmwiOiJodHRwOi8vd3d3LnpoZW5ndG9uZ2l0LmNvbS8iLCJjbGllbnRfaWQiOiJjZW50ZXIifQ.axN9O86ldfr00Sjo4ERzc0GxFtgeHAMV2hqlTL2xQHk",
    "expires_in": 7200,
    "scope": "select",
    "url": "http://www.zhengtongit.com/",
    "jti": "cd20d436-061c-4d13-a469-343f5439e01b"
}
```
### 3.授权码模式
   ###  3-1.获取授权码 
   关键值response_type=code
```http request
http://localhost:8020/oauth/authorize?response_type=code&scope=select&client_id=first
```
   如果未登陆则跳到登陆页面，输入用户名和密码，选择“授权”，返回授权码。
   ###  3-2.获取token 
   关键值grant_type=authorization_code
```http request
http://localhost:8020/oauth/token?grant_type=authorization_code&scope=select&client_id=first&client_secret=123456&code=5dylWH
```
   ### 返回数据(json)
```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoidXNlcl8xIiwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU1NDIwMzc5OSwiYXV0aG9yaXRpZXMiOlsib2F1dGgyIl0sImp0aSI6IjQ1NTEyNDQ5LTRmMzItNGNhMS04N2Q3LTM0MmYxMmZmZjgyOSIsInVybCI6Imh0dHA6Ly93d3cuemhlbmd0b25naXQuY29tLyIsImNsaWVudF9pZCI6ImF1dGhfdGVzdCJ9.A50XSQDKWdACbo9PUs_Fsyqkwz1Gdn1Q6maySG1AgnpHimtrumShNctHzLip5sT3D1N25xF3CjglTPRPvNjFvkEwTJOPa-GrdwST3RkuJE_y6rti_p5EHVdfvA19ldsjbuHXxqKGEzXiYerx8vye9aPinKdEUCUPUF_ykMD5OsC5X4TLvZ9_W_y2-Z8lx7SMVsut3fLR4HA8hFdrpan87bROksAYrRDaVEng-WZtuXvwHIvA4afLNvy1JlD1L-lTDJZRvwG4bmiHElmA0w19jbkEDyXd2uByjDh6k_d_W_n4Xdny6trmO2pVvjQT5hX2aqjIMLVzwOSisncwDZvDfA",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoidXNlcl8xIiwic2NvcGUiOlsic2VsZWN0Il0sImF0aSI6IjQ1NTEyNDQ5LTRmMzItNGNhMS04N2Q3LTM0MmYxMmZmZjgyOSIsImV4cCI6MTU1NDQ1NTc5OSwiYXV0aG9yaXRpZXMiOlsib2F1dGgyIl0sImp0aSI6ImRiYTMwODc3LWMzMDktNDJhYy05M2EzLTNjMzYxMDE0YjFmMSIsInVybCI6Imh0dHA6Ly93d3cuemhlbmd0b25naXQuY29tLyIsImNsaWVudF9pZCI6ImF1dGhfdGVzdCJ9.e6DwNlxw-MKVd045cy1Hrq6A7NRbp89u5p82AieMJZEUiVqOZO90bCcrtnLWEbmoua2FIPu9QjhWEUYAEz09h2C0b9SMVPCZqzwn_UmPzMvvME_J-deI0lznfDS1_elfcDQG-NJkQmxBdG55RPB8HznBR4ykHmDlVhIFedMIVwOfpV7WCZ2f0EtxXI5CpyDOYgIGyu70oSqC25wX9hH_5MuGm6tbq0tCQNxyoUCW2gPqFoMdGiUA1hU_2BtCcR2uZbI-UiVZGRu01l8hTexA12NRVfp4feq1BtPhzGinfY92yJPMkBoUgMSZy6Fm9wKthhCzzF4aT7OIHWau1rzvwA",
    "expires_in": 7199,
    "scope": "select",
    "url": "http://www.zhengtongit.com/",
    "jti": "45512449-4f32-4ca1-87d7-342f12fff829"
}
```
   
   
### 对于expires_in属性如果小于10，需要手动刷新token，此时expires_in恢复到7200。默认情况下client模式不支持刷新token，password模式支持刷新token。

```http request
http://localhost:8020/oauth/token?grant_type=refresh_token&refresh_token=refresh_token(需修改refresh_token)&client_id=center&client_secret=123456
```

### 验证token
```http request
http://localhost:8020/oauth/check_token?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoidXNlcl8xIiwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU1MTg3MDg4OCwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiJjZDIwZDQzNi0wNjFjLTRkMTMtYTQ2OS0zNDNmNTQzOWUwMWIiLCJ1cmwiOiJodHRwOi8vd3d3LnpoZW5ndG9uZ2l0LmNvbS8iLCJjbGllbnRfaWQiOiJjZW50ZXIifQ.d_ex8ui6gsz2YfTaTdiuu2YUWXZRtSl9b2sMOfosq84
```
### JDK keytool 生成非对称加密证书
```html
1 keytool -genkeypair -alias ztcloud -keyalg RSA -keypass zhengtongit -keystore ztcloud.jks -storepass zhengtongit
2 keytool -list -rfc --keystore ztcloud.jks | openssl x509 -inform pem -pubkey

result：

-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkrsVKdVtNqsIRaLTYciY
GPIV8RRaxFTTwGbjX1E/a88JkRemuIwQrMNT2gpLD3YvMC5Y5jQUnulYrXPdp4PK
2ZtQVunI9uC6qbrQh/9BXQLDggQPChCz8wThlnIdHL5Ed1ptF+pKWAfJTuIKgtjc
oALxI2ms0GCO6hvZe1bL94izdPzF77EkhmyLrO4tNip43MNl9HOB2RRKxHOUXUH/
PujcOSBP6xSj5zCc0ugXsl5rGiiZOKr/IRUWN4+roZHa0XH6NY9ean9vqdwX9aQn
sNJPmKaqQxuXBLUJpzaazLFVao7QMYrWafU9UOmQDXKe8uUzKV01YQEYDYCcEfc7
RQIDAQAB
-----END PUBLIC KEY-----
-----BEGIN CERTIFICATE-----
MIIDdTCCAl2gAwIBAgIEKQsAMDANBgkqhkiG9w0BAQsFADBrMRAwDgYDVQQGEwdV
bmtub3duMQswCQYDVQQIEwJjbjEPMA0GA1UEBxMGc2hhYXhpMQ0wCwYDVQQKEwR4
aWFuMRIwEAYDVQQLEwl6aGVuZ3RvbmcxFjAUBgNVBAMTDTE5Mi4xNjguMS4xNTMw
HhcNMTkwNDAxMTI0NDMzWhcNMTkwNjMwMTI0NDMzWjBrMRAwDgYDVQQGEwdVbmtu
b3duMQswCQYDVQQIEwJjbjEPMA0GA1UEBxMGc2hhYXhpMQ0wCwYDVQQKEwR4aWFu
MRIwEAYDVQQLEwl6aGVuZ3RvbmcxFjAUBgNVBAMTDTE5Mi4xNjguMS4xNTMwggEi
MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCSuxUp1W02qwhFotNhyJgY8hXx
FFrEVNPAZuNfUT9rzwmRF6a4jBCsw1PaCksPdi8wLljmNBSe6Vitc92ng8rZm1BW
6cj24LqputCH/0FdAsOCBA8KELPzBOGWch0cvkR3Wm0X6kpYB8lO4gqC2NygAvEj
aazQYI7qG9l7Vsv3iLN0/MXvsSSGbIus7i02Knjcw2X0c4HZFErEc5RdQf8+6Nw5
IE/rFKPnMJzS6BeyXmsaKJk4qv8hFRY3j6uhkdrRcfo1j15qf2+p3Bf1pCew0k+Y
pqpDG5cEtQmnNprMsVVqjtAxitZp9T1Q6ZANcp7y5TMpXTVhARgNgJwR9ztFAgMB
AAGjITAfMB0GA1UdDgQWBBRRQRK75z4x6DZSnfDqYgRiMjvLdDANBgkqhkiG9w0B
AQsFAAOCAQEAirjjOUUwq70sLj463J/qNsIJLv8wEhRPd0HMv54dCjfvZk0RmxLj
Kn3VUWpTTOBc2X1q0qPkx54MqiF6FFFYmiuxhXSFAVAyd1iHwuqc9tN7fvH5p+AJ
RWOAeEx1tsJ9pTVgA3P0/rFJ85Q8Hj2k9VxhPUqX8J/0zjx152nBQ/Bgs0pzFL4S
bDbozaq1L3icpDZNNi0aH7g0ugWfHQBL3/XMPlK1B/KIN7Rbszut4jHrDI5/KcCf
oy9rLdkkGruGE2uIc27UOB/sr3VSRFd/kGRL67Pqt6XB4nBitDH0H5zyETZf99fi
ov0dSmBGMUO6PCnwOApeOLXguYvwpjIYnQ==
-----END CERTIFICATE-----
```
