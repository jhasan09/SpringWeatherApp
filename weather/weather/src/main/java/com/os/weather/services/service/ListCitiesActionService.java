package com.os.weather.services.service;
import com.os.weather.common.ActionInterface;
import com.os.weather.services.BaseService;
import groovy.sql.GroovyRowResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ListCitiesActionService extends BaseService implements ActionInterface {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static String SUCCESS_MESSAGE = "Successfully Displayed";

    @Override
    public Map executePreCondition(Map parameters) {
        return parameters;
    }
    @Override
    public Map execute(Map previousResult) {
        try {
            String preparedQuery = "SELECT * FROM liked_city";
            List<GroovyRowResult> queryList = executeSelectSql(preparedQuery);
            previousResult.put("queryList", queryList);
            return previousResult;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Map executePostCondition(Map previousResult) {
        return previousResult;
    }

    @Override
    public Map buildSuccessResult(Map executeResult) {
        return super.setSuccess(executeResult, SUCCESS_MESSAGE);
    }

    @Override
    public Map buildFailureResult(Map executeResult) {
        return executeResult;
    }

}
