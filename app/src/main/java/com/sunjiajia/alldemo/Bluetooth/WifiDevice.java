package com.sunjiajia.alldemo.Bluetooth;

/**
 * Created by mk on 2016/12/30.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Iterator;

/**
 * Describes information about a detected Wi-Fi STA.
 * {@hide}
 */
public class WifiDevice implements Parcelable {
    /**
     * The device MAC address is the unique id of a Wi-Fi STA
     */
    public String deviceAddress = "";

    /**
     * The device name is a readable string of a Wi-Fi STA
     */
    public String deviceName = "";

    /**
     * The device state is the state of a Wi-Fi STA
     */
    public int deviceState = 0;

    /**
     * These definitions are for deviceState
     */
    public static final int DISCONNECTED = 0;
    public static final int CONNECTED = 1;
    public static final int BLACKLISTED = 2;

    private static final String AP_STA_CONNECTED_STR = "AP-STA-CONNECTED";
    private static final String AP_STA_DISCONNECTED_STR = "AP-STA-DISCONNECTED";
    private static final String AP_STA_REPORT_STR = "AP-STA-REPORT";

    /**
     * {@hide}
     */
    public WifiDevice() {
    }

    /**
     * @param string formats supported include
     *               <p>
     *               AP-STA-CONNECTED 42:fc:89:a8:96:09
     *               AP-STA-DISCONNECTED 42:fc:89:a8:96:09
     *               <p>
     *               Note: The events formats can be looked up in the hostapd code
     * @hide
     */
    public WifiDevice(String dataString) throws IllegalArgumentException {
        String[] tokens = dataString.split(" ");

        if (tokens.length < 2) {
            throw new IllegalArgumentException();
        }

        if (tokens[0].indexOf(AP_STA_CONNECTED_STR) != -1) {
            deviceState = CONNECTED;
        } else if (tokens[0].indexOf(AP_STA_DISCONNECTED_STR) != -1) {
            deviceState = DISCONNECTED;
        } else if (tokens[0].indexOf(AP_STA_REPORT_STR) != -1) {
            deviceState = BLACKLISTED;
        } else {
            throw new IllegalArgumentException();
        }

        deviceAddress = tokens[1];
    }

    protected WifiDevice(Parcel in) {
        deviceAddress = in.readString();
        deviceName = in.readString();
        deviceState = in.readInt();
    }

    public static final Creator<WifiDevice> CREATOR = new Creator<WifiDevice>() {
        @Override
        public WifiDevice createFromParcel(Parcel in) {
            return new WifiDevice(in);
        }

        @Override
        public WifiDevice[] newArray(int size) {
            return new WifiDevice[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof WifiDevice)) {
            return false;
        }

        WifiDevice other = (WifiDevice) obj;

        if (deviceAddress == null) {
            return (other.deviceAddress == null);
        } else {
            return deviceAddress.equals(other.deviceAddress);
        }
    }

    /**
     * Implement the Parcelable interface {@hide}
     */
    public int describeContents() {
        return 0;
    }

    /**
     * Implement the Parcelable interface {@hide}
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceAddress);
        dest.writeString(deviceName);
        dest.writeInt(deviceState);
    }
/*
    *//** Implement the Parcelable interface {@hide} *//*
    public static final Creator<wifidevice> CREATOR =
            new Creator<wifidevice>() {
                public WifiDevice createFromParcel(Parcel in) {
                    WifiDevice device = new WifiDevice();
                    device.deviceAddress = in.readString();
                    device.deviceName = in.readString();
                    device.deviceState = in.readInt();
                    return device;
                }

                public WifiDevice[] newArray(int size) {
                    return new WifiDevice[size];
                }
            };

    public List<wifidevice> getTetherConnectedSta() {
        Iterator it;
        List<wifidevice> TetherConnectedStaList = new ArrayList<wifidevice>();

        if (mContext.getResources().getBoolean(com.android.internal.R.bool.config_softap_extention)) {
            it = mConnectedDeviceMap.keySet().iterator();
            while(it.hasNext()) {
                String key = (String)it.next();
                WifiDevice device = (WifiDevice)mConnectedDeviceMap.get(key);
                if (VDBG) {
                    Log.d(TAG, "getTetherConnectedSta: addr=" + key + " name=" + device.deviceName);
                }
                TetherConnectedStaList.add(device);
            }
        }

        return TetherConnectedStaList;
    }*/
}
