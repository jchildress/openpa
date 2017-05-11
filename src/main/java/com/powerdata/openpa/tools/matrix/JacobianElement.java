package com.powerdata.openpa.tools.matrix;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

/**
 * Jacobian Element
 * <p>
 * Store and access Jacobian results.
 *
 * @author chris@powerdata.com
 */

public interface JacobianElement {
    /**
     * get the partial derivative of active power with respect to voltage angle
     */
    float getDpda();

    /**
     * set the partial derivative of active power with respect to voltage angle
     */
    void setDpda(float v);

    /**
     * get the partial derivative of active power with respect to voltage magnitude
     */
    float getDpdv();

    /**
     * set the partial derivative of active power with respect to voltage magnitude
     */
    void setDpdv(float v);

    /**
     * get the partial derivative of reactive power with respect to voltage angle
     */
    float getDqda();

    /**
     * set the partial derivative of reactive power with respect to voltage angle
     */
    void setDqda(float v);

    /**
     * get the partial derivative of reactive power with respect to voltage magnitude
     */
    float getDqdv();

    /**
     * set the partial derivative of reactive power with respect to voltage magnitude
     */
    void setDqdv(float v);

    /**
     * increment the partial derivative of active power with respect to voltage angle
     */
    void incDpda(float v);

    /**
     * increment the partial derivative of active power with respect to voltage magnitude
     */
    void incDpdv(float v);

    /**
     * increment the partial derivative of reactive power with respect to voltage angle
     */
    void incDqda(float v);

    /**
     * increment the partial derivative of reactive power with respect to voltage magnitude
     */
    void incDqdv(float v);

    /**
     * decrement the partial derivative of active power with respect to voltage angle
     */
    void decDpda(float v);

    /**
     * decrement the partial derivative of active power with respect to voltage magnitude
     */
    void decDpdv(float v);

    /**
     * decrement the partial derivative of reactive power with respect to voltage angle
     */
    void decDqda(float v);

    /**
     * decrement the partial derivative of reactive power with respect to voltage magnitude
     */
    void decDqdv(float v);

    /**
     * Increment all partial derivatives of this element with the given element
     */
    void inc(JacobianElement e);

    /**
     * Decrement all partial derivatives of this element with the given element
     */
    void dec(JacobianElement e);


    /**
     * A simple container for the partial derivatives in our Jacobian Element.
     *
     * @author chris@powerdata.com
     */
    static class JacobianElementContainer implements JacobianElement {
        float _dpda, _dpdv, _dqda, _dqdv;

        public JacobianElementContainer(float dpda, float dpdv, float dqda, float dqdv) {
            _dpda = dpda;
            _dpdv = dpdv;
            _dqda = dqda;
            _dqdv = dqdv;
        }

        @Override
        public float getDpda() {
            return _dpda;
        }

        @Override
        public void setDpda(float v) {
            _dpda = v;
        }

        @Override
        public float getDpdv() {
            return _dpdv;
        }

        @Override
        public void setDpdv(float v) {
            _dpdv = v;
        }

        @Override
        public float getDqda() {
            return _dqda;
        }

        @Override
        public void setDqda(float v) {
            _dqda = v;
        }

        @Override
        public float getDqdv() {
            return _dqdv;
        }

        @Override
        public void setDqdv(float v) {
            _dqdv = v;
        }

        @Override
        public void incDpda(float v) {
            _dpda += v;
        }

        @Override
        public void incDpdv(float v) {
            _dpdv += v;
        }

        @Override
        public void incDqda(float v) {
            _dqda += v;
        }

        @Override
        public void incDqdv(float v) {
            _dqdv += v;
        }

        @Override
        public void decDpda(float v) {
            _dpda -= v;
        }

        @Override
        public void decDpdv(float v) {
            _dpdv -= v;
        }

        @Override
        public void decDqda(float v) {
            _dqda -= v;
        }

        @Override
        public void decDqdv(float v) {
            _dqdv -= v;
        }

        @Override
        public void inc(JacobianElement e) {
            _dpda += e.getDpda();
            _dpdv += e.getDpdv();
            _dqda += e.getDqda();
            _dqdv += e.getDqdv();
        }

        @Override
        public void dec(JacobianElement e) {
            _dpda -= e.getDpda();
            _dpdv -= e.getDpdv();
            _dqda -= e.getDqda();
            _dqdv -= e.getDqdv();
        }

        @Override
        public String toString() {
            return String.format("[dpda:%f,dpdv:%f,dqda:%f,dqdv:%f]", _dpda, _dpdv, _dqda, _dqdv);
        }

    }
}
