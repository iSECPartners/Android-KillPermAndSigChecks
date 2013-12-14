Android-KillPermAndSigChecks
============================
Bypass signature and permission checks for IPCs.

Description
-------
It leverages Cydia Substrate to bypass signature and permission checks for IPCs.

Usage
-------
* Install Android-KillPermAndSigChecks.apk on a device where Cydia Substrate is installed with:

        adb install Android-KillPermAndSigChecks.apk

* Cydia Substrate can be found on Google Play 
(https://play.google.com/store/apps/details?id=com.saurik.substrate&hl=en) 
and requires a rooted device.

Notes
-------
* This should only be used on test devices
* The code also contains code to bypass more general signature checks but it is disabled for now.
