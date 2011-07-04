This is Apache Vysper running on Android. It currently requires Vysper SVN trunk and the following patch to [Apache MINA 2.0.4](http://svn.apache.org/repos/asf/mina/branches/2.0.4/):

    Index: src/main/java/org/apache/mina/filter/ssl/SslHandler.java 
    =================================================================== 
    --- src/main/java/org/apache/mina/filter/ssl/SslHandler.java	(revision 1142628) 
    +++ src/main/java/org/apache/mina/filter/ssl/SslHandler.java	(working copy) 
    @@ -509,6 +510,7 @@ 
             for (;;) { 
                 switch (handshakeStatus) { 
                     case FINISHED: 
                   + case NOT_HANDSHAKING: 
                         if ( LOGGER.isDebugEnabled()) { 
                             LOGGER.debug("{} processing the FINISHED state", sslFilter.getSessionInfo(session)); 
                         } 
                      
To build, you need the Android SDK installed, you also need to create a file called "local.properties" in the project root, containing the following:

    sdk.dir=<path to your Android SDK installation>

To build a debug distribution:

    ant debug

You then need to start the emulator or connect the device 

To deploy to a running emulator (or device):

    adb install -r bin/vysper-on-android-debug.apk

To get a log from the emulator showing Vysper starting:

    adb logcat

To set up port forwarding to allow a XMPP client to connect the running Vysper server:

    adb forward tcp:5222 tcp:5222

You should now be able to connect an XMPP client to port 5222 on the host computer, which will be forwarded to Vysper running on Android. Pretty cool.