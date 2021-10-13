#include <jni.h>
#include <string>
#include "mtc_log.h"
#include "TestLock.h"

void swap(int &p1, int &p2) //此处函数的形参p1, p2都是引用
{
    int p;
    p = p1;
    p1 = p2;
    p2 = p;
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_laotie_example_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_laotie_example_MainActivity_testQuote(JNIEnv *env, jobject /* this */) {

    int  a = 1;
    int *b;
    *b = a;

//    swap(a, *b);
    LOGD("a:%d, *b:%d,  &b:%d, &a:%d, b:%d", a, *b, &b, &a, b);
    a = 2;
    LOGD("a:%d, *b:%d,  &b:%d, &a:%d, b:%d", a, *b, &b, &a, b);
    *b = 3;
    LOGD("a:%d, *b:%d,  &b:%d, &a:%d, b:%d", a, *b, &b, &a, b);

}

extern "C" JNIEXPORT void JNICALL
Java_com_laotie_example_MainActivity_testWaitLock(JNIEnv *env, jobject /* this */) {

    TestLock::getInstance().testWaitLock();
}