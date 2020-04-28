package io.mycat.commands;

import io.mycat.MycatDataContext;
import io.mycat.client.SQLRequest;
import io.mycat.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DefErrorCommand implements MycatCommand {
    final static Logger logger = LoggerFactory.getLogger(DefErrorCommand.class);

    @Override
    public boolean run(SQLRequest request, MycatDataContext context, Response response) {
        String errorMessage = request.getOrDefault("errorMessage", "may be unknown error");
        int errorCode = Integer.parseInt(request.getOrDefault("errorCode", "-1"));
        response.sendError(errorMessage, errorCode);
        return true;
    }

    @Override
    public boolean explain(SQLRequest request,MycatDataContext context, Response response) {
        String errorMessage = request.getOrDefault("errorMessage", "may be unknown error");
        int errorCode = Integer.parseInt(request.getOrDefault("errorCode", "-1"));
        Map<String, Object> map = new HashMap<>();
        map.put("errorMessage", errorMessage);
        map.put("errorCode", errorCode);
        response.sendExplain(DefErrorCommand.class, map);
        return true;
    }

    @Override
    public String getName() {
        return "error";
    }
}