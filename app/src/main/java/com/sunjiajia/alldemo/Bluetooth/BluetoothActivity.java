package com.sunjiajia.alldemo.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sunjiajia.androidnewwidgetsdemo.R;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by mk on 2016/12/29.
 */

public class BluetoothActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "BluetoothActivity";

    private BluetoothAdapter adapter;

/*    getName()获取本地蓝牙名称
    getRemoteDevice(String address)根据蓝牙地址获取远程蓝牙设备
    getState()获取本地蓝牙适配器当前状态（感觉可能调试的时候更需要）
    isDiscovering()判断当前是否正在查找设备，是返回true
    isEnabled()判断蓝牙是否打开，已打开返回true，否则，返回false
    listenUsingRfcommWithServiceRecord(String name,UUID uuid)根据名称，UUID创建并返回BluetoothServerSocket，这是创建BluetoothSocket服务器端的第一步
    startDiscovery()开始搜索，这是搜索的第一步
    2.BluetoothDevice看名字就知道，这个类描述了一个蓝牙设备
    createRfcommSocketToServiceRecord(UUIDuuid)根据UUID创建并返回一个BluetoothSocket
    这个方法也是我们获取BluetoothDevice的目的——创建BluetoothSocket
    这个类其他的方法，如getAddress(),getName(),同BluetoothAdapter
    3.BluetoothServerSocket如果去除了Bluetooth相信大家一定再熟悉不过了，既然是Socket，方法就应该都差不多，
    两个重载的accept(),accept(inttimeout)两者的区别在于后面的方法指定了过时时间，需要注意的是，执行这两个方法的时候，直到接收到了客户端的请求（或是过期之后），都会阻塞线程，应该放在新线程里运行！
  还有一点需要注意的是，这两个方法都返回一个BluetoothSocket，最后的连接也是服务器端与客户端的两个BluetoothSocket的连接
      close()这个就不用说了吧，翻译一下——关闭！
    4.BluetoothSocket,跟BluetoothServerSocket相对，是客户端
一共5个方法，不出意外，都会用到
      close(),关闭
      connect()连接
      getInptuStream()获取输入流
      getOutputStream()获取输出流
      getRemoteDevice()获取远程设备，这里指的是获取bluetoothSocket指定连接的那个远程蓝牙设备
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_activity);
        initViews();
    }

    private void initViews() {
        findViewById(R.id.openBluetooth).setOnClickListener(this);
        findViewById(R.id.closeBluetooth).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.openBluetooth:
                openBlueTooth();
                break;
            case R.id.closeBluetooth:
                adapter.disable();
                adapter.cancelDiscovery();
                break;
        }
    }

    //打开蓝牙
    private void openBlueTooth() {
        Log.e(TAG, "openBlueTooth");
        adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.enable();//打开蓝牙不带用户提醒
        if (adapter == null) {
            //设备不支持蓝牙
            Toast.makeText(this, "设备部支持蓝牙", Toast.LENGTH_SHORT).show();
        }
        if (!adapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //设置蓝牙可见性，最多300秒
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 4000);
            startActivity(intent);//打开蓝牙带用户提醒
            serchBluetoothDevices();
            adapter.getAddress();//获取本地蓝牙地址
        }
    }

    //搜索周围蓝牙设备
    private void serchBluetoothDevices() {
//        手机蓝牙开启关闭时发送
//        action: BluetoothAdapter.ACTION_STATE_CHANGED

        Log.e(TAG, "serchBluetoothDevices");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_CLASS_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
//        intentFilter.addAction(BluetoothDevice.ACTION_SCAN_MODE_CHANGED);
//        intentFilter.addAction(BluetoothDevice.ACTION_STATE_CHANGED);
        // 注册广播接收器，接收并处理搜索结果
        registerReceiver(receiver, intentFilter);
        //寻找蓝牙设备，android 会将查找到的设备以广播的形式发出去
        adapter.startDiscovery();
    }

    int connectState;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e(TAG, "receiver  action = " + action);
            BluetoothAdapterListener(action,intent);

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 获取查找到的蓝牙设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                System.out.println(device.getName());
                //如果查找到的设备符合要连接的设备，处理
                if (device.getName().equalsIgnoreCase("aaa")) {
                    //搜索蓝牙设备的过程占用资源比较多，一旦找到需要连接的设备后需要及时关闭搜索
                    adapter.cancelDiscovery();
                    //获取蓝牙设备的链接状态
                    connectState = device.getBondState();
                    switch (connectState) {
                        //未配对
                        case BluetoothDevice.BOND_NONE:
                            //配对
                            try {
                                Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                                createBondMethod.invoke(device);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case BluetoothDevice.BOND_BONDED://已配对
                            try {
                                // 连接
                                connect(device);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            break;
                    }
                }
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                //状态改变的广播
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getName().equalsIgnoreCase("aaa")) {
                    connectState = device.getBondState();
                    switch (connectState) {
                        case BluetoothDevice.BOND_NONE:
                            break;
                        case BluetoothDevice.BOND_BONDING:
                            break;
                        case BluetoothDevice.BOND_BONDED:
                            try {
                                //连接
                                connect(device);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            }
        }
    };

    //蓝牙设备的配对和设备监听
    private void connect(BluetoothDevice device) throws IOException {
        //固定的UUID
        Log.e(TAG, "connect");
        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);
        BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);
        socket.connect();
    }

    //监听蓝牙打开状态
    private void BluetoothAdapterListener(String action,Intent intent){
        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    Log.d("aaa", "STATE_OFF 手机蓝牙关闭");
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Log.d("aaa", "STATE_TURNING_OFF 手机蓝牙正在关闭");
                    break;
                case BluetoothAdapter.STATE_ON:
                    Log.d("aaa", "STATE_ON 手机蓝牙开启");
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    Log.d("aaa", "STATE_TURNING_ON 手机蓝牙正在开启");
                    break;
            }
        }

    }

}
