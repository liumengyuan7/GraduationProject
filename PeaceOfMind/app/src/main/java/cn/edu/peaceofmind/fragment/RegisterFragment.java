package cn.edu.peaceofmind.fragment;

import android.graphics.Color;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.utils.XToastUtils;

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
    boolean isFirstChecked,isSecondChecked = false;
    //再次获取密码
    @BindView(R.id.et_password_second)
    MaterialEditText etPwdSecond;
    @BindView(R.id.btn_eyes_second)
    ImageView btnEyesSecond;
    boolean secondChecked = false;
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
//        titleBar.addAction(new TitleBar.TextAction(R.string.title_back) {
//            @Override
//            public void performAction(View view) {
//                onRegisterSuccess();
//            }
//        });
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
        btnEyesSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSecondChecked) {
                    //如果选中，显示密码
                    btnEyesSecond.setImageResource(R.drawable.ic_open_eyes);
                    etPwdSecond.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    btnEyesSecond.setImageResource(R.drawable.ic_close_eyes);
                    etPwdSecond.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isSecondChecked = !isSecondChecked;
                etPwdSecond.setSelection(etPwdSecond.getText().length());
                etPwdSecond.postInvalidate();
            }
        });
    }

    @OnClick({R.id.btn_get_verify_code, R.id.btn_register, R.id.tv_user_protocol, R.id.tv_privacy_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_verify_code:
                if (etPhoneNumber.validate()) {
                    getVerifyCode(etPhoneNumber.getEditValue());
                }
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
        XToastUtils.warning("只是演示，验证码请随便输");
        mCountDownHelper.start();
    }

    /**
     * 根据密码登录
     *
     * @param phoneNumber 手机号
     * @param passWord 密码
     */
    private void registerByVerifyCode(String phoneNumber, String passWord) {
        // TODO: 2020/8/29 这里只是界面演示而已
        onRegisterSuccess();
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

    /**
     *动态判断两次输入的密码是否一致
    */

//    public void cmpFirstAndSecond(){
//        if (etPwdFirst.validate()){
//            if (etPwdSecond.validate()){
//                if(!etPwdSecond.equals(etPwdFirst)){
//                    XToastUtils.error("两次密码输入不一致，请重新确认");
//                }
//            }
//        }
//
//    }
    @Override
    public void onDestroyView() {
        if (mCountDownHelper != null) {
            mCountDownHelper.recycle();
        }
        super.onDestroyView();
    }
}