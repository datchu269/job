package com.example.customvalidation.exception;

import com.example.customvalidation.entity.ActionRequest;
import com.example.customvalidation.entity.ErrorInfo;
import com.example.customvalidation.entity.Header;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class LevelInfoException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(TopLevelException.class);

    private Header header;
    private ActionRequest actionRequest;
    private ErrorInfo errorInfo;

    public LevelInfoException(Header header, ActionRequest actionRequest) {
        this.header = header;
        this.actionRequest = actionRequest;
    }

    public LevelInfoException(Header header, ActionRequest actionRequest, ErrorInfo errorInfo) {
        this.header = header;
        this.actionRequest = actionRequest;
        this.errorInfo = errorInfo;
    }
}
