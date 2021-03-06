/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.regression.view;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.supportregression.bean.SupportBean;
import com.espertech.esper.supportregression.execution.RegressionExecution;

import static org.junit.Assert.assertEquals;

public class ExecViewGroupWinTypes implements RegressionExecution {
    public void run(EPServiceProvider epService) throws Exception {
        String viewStmt = "select * from " + SupportBean.class.getName() +
                "#groupwin(intPrimitive)#length(4)#groupwin(longBoxed)#uni(doubleBoxed)";
        EPStatement stmt = epService.getEPAdministrator().createEPL(viewStmt);

        assertEquals(Integer.class, stmt.getEventType().getPropertyType("intPrimitive"));
        assertEquals(Long.class, stmt.getEventType().getPropertyType("longBoxed"));
        assertEquals(Double.class, stmt.getEventType().getPropertyType("stddev"));
        assertEquals(8, stmt.getEventType().getPropertyNames().length);
    }
}
