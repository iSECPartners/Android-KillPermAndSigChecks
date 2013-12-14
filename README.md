Android-KillPermAndSigChecks
============================

Bypass signature and permission checks for IPCs.

Description
-----------

This tool leverages Cydia Substrate to bypass signature and permission checks for IPCs.

Usage
-----

* Ensure that Cydia Substrate has been deployed on your test device. The installer requires a rooted device and can be found on the Google Play store at https://play.google.com/store/apps/details?id=com.saurik.substrate&hl=en 
* Download the pre-compiled APK available at https://github.com/iSECPartners/Android-KillPermAndSigChecks/releases
* Install the APK package on the device:

        adb install Android-KillPermAndSigChecks.apk

Notes
-----
* This should only be used on test devices
* The code also contains code to bypass more general signature checks but it is disabled for now.
