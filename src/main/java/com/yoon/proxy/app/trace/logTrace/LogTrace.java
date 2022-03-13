package com.yoon.proxy.app.trace.logTrace;

import com.yoon.proxy.app.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
