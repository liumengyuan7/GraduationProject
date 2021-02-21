package cn.edu.peaceofmind.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.edu.peaceofmind.activity.MainActivity;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.utils.XToastUtils;

@Page(anim = CoreAnim.none)//设置切换进入当前类时无动画
public class LoginFragment extends XPageFragment {
    //获取手机号
    @BindView(R.id.et_phone_number)
    MaterialEditText etPhoneNumber;
    //获取密码
    @BindView(R.id.et_password)
    MaterialEditText etPassWord;
    @BindView(R.id.btn_eyes_login)
    ImageView btnEyesLogin;
    boolean isPwdChecked = false;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected TitleBar initTitleBar() {
        TitleBar titleBar = super.initTitleBar().setImmersive(true);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle("");
        titleBar.setLeftImageDrawable(ResUtils.getVectorDrawable(getContext(), R.drawable.ic_login_close));
        titleBar.setActionTextColor(ThemeUtils.resolveColor(getContext(), R.attr.colorAccent));
        titleBar.addAction(new TitleBar.TextAction(R.string.title_jump_login) {
            @Override
            public void performAction(View view) {
                onLoginSuccess();
            }
        });
        return titleBar;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        btnEyesLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPwdChecked) {
                    //如果选中，显示密码
                    btnEyesLogin.setImageResource(R.drawable.ic_open_eyes);
                    etPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    btnEyesLogin.setImageResource(R.drawable.ic_close_eyes);
                    etPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isPwdChecked = !isPwdChecked;
                etPassWord.setSelection(etPassWord.getText().length());
                etPassWord.postInvalidate();
            }
        });
    }
    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget_password, R.id.tv_user_protocol, R.id.tv_privacy_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (etPhoneNumber.validate()) {
                    if (etPassWord.validate()) {
                        loginByVerifyCode(etPhoneNumber.getEditValue(), etPassWord.getEditValue());
                    }
                }
                break;
            case R.id.tv_register:
                openPage(RegisterFragment.class);
                break;
            case R.id.tv_forget_password:
//                XToastUtils.info("忘记密码");
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
     * 获取密码
     */
    private void getVerifyCode(String phoneNumber) {
        // TODO: 2020/8/29 这里只是界面演示而已
        XToastUtils.warning("只是演示，密码请随便输");
//        mCountDownHelper.start();
    }

    /**
     * 根据密码登录
     *
     * @param phoneNumber 手机号
     * @param passWord 密码
     */
    private void loginByVerifyCode(String phoneNumber, String passWord) {
        // TODO: 2020/8/29 这里只是界面演示而已
        onLoginSuccess();
    }

    /**
     * 登录成功的处理
     */
    private void onLoginSuccess() {
//        String token = RandomUtils.getRandomNumbersAndLetters(16);
//        if (TokenUtils.handleLoginSuccess(token)) {
//            popToBack();
//            ActivityUtils.startActivity(MainActivity.class);
//        }
        //显示Intent跳转Activity
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            String result = msg.obj + "";
//        }
//    };
//    //用户登录
//    public void login(String phoneNumber, String passWord) {
//        new Thread(){
//            @Override
//            public void run() {
//                String result = new Utils().getConnectionResult("joke","list");
//                Message message = new Message();
//                message.obj = result;
//                handler.sendMessage(message);
//            }
//        }.start();
//    }
}