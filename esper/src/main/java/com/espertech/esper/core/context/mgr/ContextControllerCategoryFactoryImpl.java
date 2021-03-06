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
package com.espertech.esper.core.context.mgr;

import com.espertech.esper.epl.spec.ContextDetailCategory;
import com.espertech.esper.filter.FilterSpecCompiled;

import java.util.List;

public class ContextControllerCategoryFactoryImpl extends ContextControllerCategoryFactoryBase {

    private final ContextStatePathValueBinding binding;

    public ContextControllerCategoryFactoryImpl(ContextControllerFactoryContext factoryContext, ContextDetailCategory categorySpec, List<FilterSpecCompiled> filtersSpecsNestedContexts) {
        super(factoryContext, categorySpec, filtersSpecsNestedContexts);
        this.binding = factoryContext.getStateCache().getBinding(Integer.class);    // the integer ordinal of the category
    }

    public ContextStatePathValueBinding getBinding() {
        return binding;
    }

    public ContextController createNoCallback(int pathId, ContextControllerLifecycleCallback callback) {
        return new ContextControllerCategory(pathId, callback, this);
    }
}
