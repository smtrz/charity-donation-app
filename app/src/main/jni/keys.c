#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getNativeKey1(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "U2FtcGxlIHByb2plY3Qgb2YgSk5JIC0gRGFuaXlhbA==");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getBaseUrlOmise(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "https://api.omise.co/");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getBaseUrlCharity(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env,
                                "https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0/");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getSecretKey(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "skey_test_5i9j6g5cxps0mne1f40");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getPublicKey(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "pkey_test_5i9j6twpmps1pkss4xe");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getOmiseHostName(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "api.omise.co");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getCharityHostName(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "virtserver.swaggerhub.com");
}


JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getCharityKey1(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/aPvOEOW4dFQmDqYWzBfqxFjcj++kkeWNCBk7DEE7M5Q=");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getCharityKey2(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getCharityKey3(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getOmiseKey1(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/maqNsxEnwszR+xCmoGUiV636PvSM5zvBIBuupBn9AB8=");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getOmiseKey2(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/5kJvNEMw0KjrCAu7eXY5HZdvyCS13BbA0VJG1RSP91w=");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getOmiseKey3(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getBaseUrlOmiseToken(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "https://vault.omise.co/");
}