/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.lib.expression.node.func;

import buildcraft.lib.expression.NodeInliningHelper;
import buildcraft.lib.expression.api.IExpressionNode.INodeBoolean;
import buildcraft.lib.expression.api.IExpressionNode.INodeDouble;
import buildcraft.lib.expression.api.IExpressionNode.INodeLong;
import buildcraft.lib.expression.api.IExpressionNode.INodeObject;
import buildcraft.lib.expression.api.INodeFunc.INodeFuncObject;
import buildcraft.lib.expression.api.INodeStack;
import buildcraft.lib.expression.api.InvalidExpressionException;
import buildcraft.lib.expression.node.func.StringFunctionTri;
import buildcraft.lib.expression.node.value.NodeConstantObject;

// AUTO_GENERATED FILE, DO NOT EDIT MANUALLY!
public class NodeFuncObjectLongToObject<A, R> implements INodeFuncObject<R> {

    public final IFuncObjectLongToObject<A, R> function;
    private final StringFunctionTri stringFunction;
    private final Class<A> argTypeA;
    private final Class<R> returnType;

    public NodeFuncObjectLongToObject(String name, Class<A> argTypeA, Class<R> returnType, IFuncObjectLongToObject<A, R> function) {
        this(argTypeA, returnType, (a, b) -> name + "(" + a + b +  ")", function);
    }

    public NodeFuncObjectLongToObject(Class<A> argTypeA, Class<R> returnType, StringFunctionTri stringFunction, IFuncObjectLongToObject<A, R> function) {
        this.argTypeA = argTypeA;
        this.returnType = returnType;

        this.function = function;
        this.stringFunction = stringFunction;
    }

    @Override
    public Class<R> getType() {
        return returnType;
    }

    @Override
    public String toString() {
        return stringFunction.apply("{A}", "{B}");
    }

    @Override
    public INodeObject<R> getNode(INodeStack stack) throws InvalidExpressionException {

        INodeLong b = stack.popLong();
        INodeObject<A> a = stack.popObject(argTypeA);

        return new Func(a, b);
    }

    private class Func implements INodeObject<R> {
        private final INodeObject<A> argA;
        private final INodeLong argB;

        public Func(INodeObject<A> argA, INodeLong argB) {
            this.argA = argA;
            this.argB = argB;

        }

        @Override
        public Class<R> getType() {
            return returnType;
        }

        @Override
        public R evaluate() {
            return function.apply(argA.evaluate(), argB.evaluate());
        }

        @Override
        public INodeObject<R> inline() {
            return NodeInliningHelper.tryInline(this, argA, argB, (a, b) -> new Func(a, b),
                    (a, b) -> new NodeConstantObject<>(returnType, function.apply(a.evaluate(), b.evaluate()))
            );
        }

        @Override
        public String toString() {
            return stringFunction.apply(argA.toString(), argB.toString());
        }
    }

    @FunctionalInterface
    public interface IFuncObjectLongToObject<A, R> {
        R apply(A a, long b);
    }
}
