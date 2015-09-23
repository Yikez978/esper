/**************************************************************************************
 * Copyright (C) 2006-2015 EsperTech Inc. All rights reserved.                        *
 * http://www.espertech.com/esper                                                          *
 * http://www.espertech.com                                                           *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package com.espertech.esper.epl.named;

import com.espertech.esper.client.EventType;
import com.espertech.esper.core.context.util.EPStatementAgentInstanceHandle;
import com.espertech.esper.core.service.StatementResultService;
import com.espertech.esper.event.vaevent.ValueAddEventProcessor;

import java.util.List;
import java.util.Map;

/**
 * Service to manage named window dispatches, locks and processors on an engine level.
 */
public interface NamedWindowDispatchService
{
    NamedWindowTailView createTailView(EventType eventType, NamedWindowMgmtService namedWindowMgmtService, NamedWindowDispatchService namedWindowDispatchService, StatementResultService statementResultService, ValueAddEventProcessor revisionProcessor, boolean prioritized, boolean parentBatchWindow);

    /**
     * Dispatch events of the insert and remove stream of named windows to consumers, as part of the
     * main event processing or dispatch loop.
     * @return send events to consuming statements
     */
    boolean dispatch();

    /**
     * For use to add a result of a named window that must be dispatched to consuming views.
     * @param delta is the result to dispatch
     * @param consumers is the destination of the dispatch, a map of statements to one or more consuming views
     */
    void addDispatch(NamedWindowDeltaData delta, Map<EPStatementAgentInstanceHandle, List<NamedWindowConsumerView>> consumers);

    /**
     * Destroy service.
     */
    void destroy();
}
