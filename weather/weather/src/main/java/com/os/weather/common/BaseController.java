package com.os.weather.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class BaseController {
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    @Autowired
    private ObjectMapper mapperObj;
    protected BaseController() {
    }

    //@ResponseBody
    protected String renderOutput(ActionInterface action, Map<String, Object> params) {
        String output = "";
        try {
            Map result = this.getServiceResponse(action, params);
            output = mapperObj.writeValueAsString(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output;
    }

    private Map getServiceResponse(ActionInterface action, Map<String, Object> params) {

        params.put(Tools.IS_ERROR, FALSE);

        Map preResult = action.executePreCondition(params);
        String isError = (String) preResult.get("isError");
        if (isError.equals(TRUE)) {
            return action.buildFailureResult(preResult);
        }

        Map executeResult = action.execute(preResult);
        isError = (String) executeResult.get("isError");
        if (isError.equals(TRUE)) {
            return action.buildFailureResult(executeResult);
        }

        Map postResult = action.executePostCondition(executeResult);
        isError = (String) postResult.get("isError");
        if (isError.equals(TRUE)) {
            return action.buildFailureResult(postResult);
        }

        return action.buildSuccessResult(postResult);
    }
}
