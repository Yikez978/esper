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
package com.espertech.esper.epl.enummethod.eval;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.codegen.core.CodegenContext;
import com.espertech.esper.codegen.core.CodegenMethodId;
import com.espertech.esper.codegen.model.expression.CodegenExpression;
import com.espertech.esper.codegen.model.method.CodegenParamSetEnumMethodNonPremade;
import com.espertech.esper.codegen.model.method.CodegenParamSetEnumMethodPremade;
import com.espertech.esper.epl.expression.core.ExprEvaluatorContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.espertech.esper.codegen.model.expression.CodegenExpressionBuilder.*;

public class EnumReverseForge implements EnumEval, EnumForge {

    private int numStreams;

    public EnumReverseForge(int numStreams) {
        this.numStreams = numStreams;
    }

    public EnumEval getEnumEvaluator() {
        return this;
    }

    public int getStreamNumSize() {
        return numStreams;
    }

    public Object evaluateEnumMethod(EventBean[] eventsLambda, Collection enumcoll, boolean isNewData, ExprEvaluatorContext context) {
        if (enumcoll.isEmpty()) {
            return enumcoll;
        }

        ArrayList<Object> result = new ArrayList<Object>(enumcoll);
        Collections.reverse(result);
        return result;
    }

    public CodegenExpression codegen(CodegenParamSetEnumMethodNonPremade args, CodegenContext context) {
        CodegenParamSetEnumMethodPremade premade = CodegenParamSetEnumMethodPremade.INSTANCE;
        CodegenMethodId method = context.addMethod(Collection.class, EnumReverseForge.class).add(premade).begin()
                .ifCondition(exprDotMethod(premade.enumcoll(), "isEmpty"))
                .blockReturn(premade.enumcoll())
                .declareVar(ArrayList.class, "result", newInstance(ArrayList.class, premade.enumcoll()))
                .expression(staticMethod(Collections.class, "reverse", ref("result")))
                .methodReturn(ref("result"));
        return localMethodBuild(method).passAll(args).call();
    }
}