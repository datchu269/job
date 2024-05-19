package com.example.customvalidation.exception;

import com.example.customvalidation.entity.ErrorInfo;
import com.example.customvalidation.entity.Header;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class TopLevelException extends Exception{
    private static final Logger logger = LoggerFactory.getLogger(TopLevelException.class);

    private Header header;
    private ErrorInfo errorInfo;

    public TopLevelException(Header header, ErrorInfo errorInfo) {
        this.header = header;
        this.errorInfo = errorInfo;
    }
}
