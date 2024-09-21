package com.campususedtrading.validation;


import com.campususedtrading.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {

    /**
     *
     * @param state 将来要校验的数据
     * @param context
     * @return
     */
    public boolean isValid(String state, ConstraintValidatorContext context) {

        //提供校验规则
        if (state == null) {
            return false;
        }
        if (state.equals("已上架") || state.equals("未通过") || state.equals("待审核") || state.equals("已售出")) {
            return true;
        }
        return false;
    }

}