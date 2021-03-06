package cn.edu.peaceofmind.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.utils.Utils;
import cn.edu.peaceofmind.utils.XToastUtils;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

@Page(anim = CoreAnim.none)
public class RegisterFragment extends XPageFragment {
    //获取手机号
    @BindView(R.id.et_phone_number)
    MaterialEditText etPhoneNumber;
    //获取密码
    @BindView(R.id.et_password_first)
    MaterialEditText etPwdFirst;
    @BindView(R.id.btn_eyes_first)
    ImageView btnEyesFirst;
    @BindView(R.id.fl_password_first)
    FrameLayout pwdLayout;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_verify_code)
    Button btnVerifyCode;
    boolean isFirstChecked,isSecondChecked = false;
    //    //再次获取密码
//    @BindView(R.id.et_password_second)
//    MaterialEditText etPwdSecond;
//    @BindView(R.id.btn_eyes_second)
//    ImageView btnEyesSecond;
//    boolean secondChecked = false;
    //获取验证码
    @BindView(R.id.et_verify_code)
    MaterialEditText etVerifyCode;
    //获取验证码
    @BindView(R.id.btn_get_verify_code)
    RoundButton btnGetVerifyCode;

    private CountDownButtonHelper mCountDownHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected TitleBar initTitleBar() {
        TitleBar titleBar = super.initTitleBar().setImmersive(true);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle("");
        titleBar.setLeftImageDrawable(ResUtils.getVectorDrawable(getContext(), R.drawable.ic_back));
        titleBar.setActionTextColor(ThemeUtils.resolveColor(getContext(), R.attr.colorAccent));
        return titleBar;
    }

    @Override
    protected void initViews() {
        mCountDownHelper = new CountDownButtonHelper(btnGetVerifyCode, 60);

        //隐私政策弹窗
//        if (!SettingUtils.isAgreePrivacy()) {
//            Utils.showPrivacyDialog(getContext(), (dialog, which) -> {
//                dialog.dismiss();
//                SettingUtils.setIsAgreePrivacy(true);
//            });
//        }
    }

    @Override
    protected void initListeners() {
        btnEyesFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstChecked) {
                    //如果选中，显示密码
                    btnEyesFirst.setImageResource(R.drawable.ic_open_eyes);
                    etPwdFirst.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    btnEyesFirst.setImageResource(R.drawable.ic_close_eyes);
                    etPwdFirst.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isFirstChecked = !isFirstChecked;
                etPwdFirst.setSelection(etPwdFirst.getText().length());
                etPwdFirst.postInvalidate();
            }
        });
    }

    @OnClick({R.id.btn_get_verify_code,R.id.btn_verify_code, R.id.btn_register, R.id.tv_user_protocol, R.id.tv_privacy_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_verify_code:
                if (etPhoneNumber.validate()) {
                    getVerifyCode(etPhoneNumber.getEditValue());
                }
                break;
            case R.id.btn_verify_code:
                // 提交验证码，其中的code表示验证码，如“1357”
                SMSSDK.submitVerificationCode("86", etPhoneNumber.getEditValue(), etVerifyCode.getEditValue());
                break;
            case R.id.btn_register:
                if (etPhoneNumber.validate()) {
                    if (etPwdFirst.validate()) {
                        registerByVerifyCode(etPhoneNumber.getEditValue(), etPwdFirst.getEditValue());
                    }
                }
                break;
            case R.id.tv_user_protocol:
                XToastUtils.info("用户协议");
                break;
            case R.id.tv_privacy_protocol:
                XToastUtils.info("隐私政策");
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getVerifyCode(String phoneNumber) {
        // TODO: 2020/8/29 这里只是界面演示而已
        mCountDownHelper.start();
        // 在尝试读取通信录时以弹窗提示用户（可选功能）
        SMSSDK.setAskPermisionOnReadContact(true);
        EventHandler eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                Log.e("验证码",data.toString()+",event:"+event+",msg:"+msg.toString());
//                new Handler(Looper.getMainLooper(), new Handler.Callback() {
//                    @Override
//                    public boolean handleMessage(Message msg) {
//                        int event = msg.arg1;
//                        int result = msg.arg2;
//                        Object data = msg.obj;
//                        Log.e("验证码",data.toString()+",event:"+event+",msg:"+msg.toString());
//                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                            if (result == SMSSDK.RESULT_COMPLETE) {
//                                // TODO 处理成功得到验证码的结果
//                                Log.e("验证码",data.toString()+",event:"+event+",msg:"+msg);
//                                // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
//                            } else {
//                                // TODO 处理错误的结果
//                                ((Throwable) data).printStackTrace();
//                            }
//                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                            if (result == SMSSDK.RESULT_COMPLETE) {
//                                // TODO 处理验证码验证通过的结果
//                                Log.e("验证码",data.toString()+",event:"+event+",msg:"+msg);
//                            } else {
//                                // TODO 处理错误的结果
//                                ((Throwable) data).printStackTrace();
//                            }
//                        }
//                        // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
//                        return false;
//                    }
//                }).sendMessage(msg);
                msgHandler.sendMessage(msg);
            }
        };
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
        // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
        SMSSDK.getVerificationCode("86", String.valueOf(etPhoneNumber.getEditValue()));

//        // 提交验证码，其中的code表示验证码，如“1357”
//        SMSSDK.submitVerificationCode("86", etPhoneNumber.getEditValue(), etVerifyCode.getEditValue());
//        // 使用完EventHandler需注销，否则可能出现内存泄漏
//        SMSSDK.unregisterEventHandler(eventHandler);
    }
//    处理UI线程
    private Handler msgHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //处理成功得到验证码的结果
                            XToastUtils.info("验证码已发送");
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        } else {
                            //处理错误的结果
                            ((Throwable) data).printStackTrace();
                            XToastUtils.info("验证失败，请重新获取验证码");
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            pwdLayout.setVisibility(View.VISIBLE);btnRegister.setVisibility(View.VISIBLE);
                            btnVerifyCode.setVisibility(View.GONE);

                            Log.e("验证码",data.toString()+",event:"+event+",msg:"+msg);
                            XToastUtils.info("验证成功");
                        } else {
                    //处理错误的结果
                    XToastUtils.info("验证失败，请重新获取验证码");
                    ((Throwable) data).printStackTrace();
                }
            }
        }
    };
    /**
     * 根据密码注册
     *
     * @param phoneNumber 手机号
     * @param passWord 密码
     */
    private void registerByVerifyCode(String phoneNumber, String passWord) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passWord.getBytes());
            passWord = new BigInteger(1, md.digest()).toString(18);
            Log.e("md5",passWord);
            userRegister(phoneNumber,passWord, "peaceofmind"+new Random().nextInt());
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        onRegisterSuccess();
    }

    /**
     * 注册成功的处理
     */
    private void onRegisterSuccess() {
//        String token = RandomUtils.getRandomNumbersAndLetters(16);
//        if (TokenUtils.handleLoginSuccess(token)) {
//            popToBack();//结束当前fragment
//            ActivityUtils.startActivity(MainActivity.class);
//        }
        openPage(LoginFragment.class);
    }

    @Override
    public void onDestroyView() {
        if (mCountDownHelper != null) {
            mCountDownHelper.recycle();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销所有EventHandler监听，避免内存泄露
        SMSSDK.unregisterAllEventHandler();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            String result = msg.obj + "";
            if(result.equals("repeat")){
                XToastUtils.info("该手机号已经被注册了，换一个吧~");
            }else if(result.equals("false")){
                XToastUtils.error("注册失败！");
            }else{
                XToastUtils.info("注册成功!");
                onRegisterSuccess();
            }
        }
    };
    //注册
    public void userRegister(String userPhone,String userPassword,String userNickname) {
        new Thread(){
            @Override
            public void run() {
                String result = new Utils().getConnectionResult("user","userRegister","userPhone="+userPhone
                +"&userPassword="+userPassword+"&userNickname="+userNickname+"&userSex=男");
                Message message = new Message();
                message.obj = result;
                handler.sendMessage(message);
            }
        }.start();
    }

}