package com.sunjiajia.alldemo.email_phone_mail;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sunjiajia.androidnewwidgetsdemo.R;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mk on 2017/2/17.
 */

public class EmailSendAndReceive extends Activity implements View.OnClickListener {

    private Button sendBtn;
    private EditText title, content, mailAddressId;
    public TextView status;

    String receive = "382340839@qq.com";//接受邮箱
    String cc = "2244541416@qq.com";//抄送邮箱
    String bcc = "dou.xinmei@morecomtech.com";//密送邮箱
    String send = "dong.tengfei@morecomtech.com";//发送邮箱
    String pwd = "!moke123?";//邮箱密码
    String subject = "Test";//发送标题
    String message = "再次见到你时，你那美丽的头发，充满了我的眼睛!";//发送文本信息

    String sendStr;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_send);
        initViews();
    }

    private void initViews() {
        sendBtn = (Button) findViewById(R.id.SendEmail);
        sendBtn.setOnClickListener(this);
        title = (EditText) findViweByIdnitViews(R.id.EmailTitle);
        content = (EditText) findViweByIdnitViews(R.id.EmailContent);
        mailAddressId = (EditText) findViweByIdnitViews(R.id.inputEmailAddress);
        status = (TextView) findViweByIdnitViews(R.id.status);
    }

    private View findViweByIdnitViews(int id) {
        return findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SendEmail:
                status.setText("");
                if (!mailAddressId.getText().toString().trim().isEmpty())
                    receive = mailAddressId.getText().toString();
                if (!title.getText().toString().trim().isEmpty())
                    subject = title.getText().toString().trim();
                if (!content.getText().toString().trim().isEmpty())
                    message = content.getText().toString().trim();

                //发送邮件任务
                new AsyncTask<String, Integer, Boolean>() {

                    @Override
                    protected Boolean doInBackground(String... params) {
                        try {
                            //sendEmilByApacheCommonsNet(send,pwd,receive,subject,message);
                            System.out.println("send=" + send + ",pwd=" + pwd + ",receive=" + receive + ",subject=" + subject + ",message="
                                    + message);
                            sendEmailByApacheCommonsEmail(send, pwd, receive, cc, bcc, subject, message);
                            return true;
                        } catch (Exception ex) {
                            Log.e("dongtengfei", "exception = " + ex.toString());
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        long time = System.currentTimeMillis();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
                        if (aBoolean){
                            status.setText("邮件发送成功" + sendStr + " /br" + format.format(time));
                        }else{
                            status.setText("邮件发送失败" + sendStr  + " /br" + format.format(time));
                        }
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                    }

                }.execute();
                break;
        }
    }

    private boolean verifyEmailAddress(String address) {
        if (null == address || "".equals(address))
            return false;
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
        Matcher m = p.matcher(address);
        return m.matches();
    }

    private void sendEmailByApacheCommonsEmail(String from, String fromPwd, String to, String cc
            , String bcc, String subject, String message) throws EmailException {
        if (!verifyEmailAddress(from) || !verifyEmailAddress(to)) {
            System.out.println("enter verifyEmailAddress");
            return;
        }
        //Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setDebug(true);
        //这里使用163邮箱服务器，实际需要修改为对应邮箱服务器
        //smtp.163.com:25 smtp.qq.com:587
//        email.setHostName("smtp.qq.com");
        email.setHostName("smtp.mxhichina.com");//阿里运邮箱
//        email.setSmtpPort(465);//163邮箱25
        email.setSocketTimeout(6 * 1000);
        email.setCharset("UTF-8");
        //email.setTLS(true);
        email.setStartTLSEnabled(true);
//        email.setSSL(true);
        email.setAuthentication(from, fromPwd);
        email.addTo(to, to);
        email.addBcc(bcc);
        email.addCc(cc);
        email.setFrom(from, from);
        email.setSubject(subject);
        email.setMsg(message);
        //Create the attachment
      /*  EmailAttachment attachment2 = new EmailAttachment();
        attachment2.setPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pdf02.png");
        Log.e("dongtengfei",Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pdf02.png");
        attachment2.setDisposition(EmailAttachment.ATTACHMENT);
        attachment2.setDescription("pdf02");
        attachment2.setName("pdf02.png");

        EmailAttachment attachment1 = new EmailAttachment();
        attachment1.setPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pdf01.png");
        attachment1.setDisposition(EmailAttachment.ATTACHMENT);
        attachment1.setDescription("pdf01");
        attachment1.setName("pdf01.png");

        email.attach(attachment1);
        email.attach(attachment2);*/
        //send the email
        sendStr = email.send();
        System.out.println("sendStr = " + sendStr);
        System.out.println("邮件发送成功");

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
