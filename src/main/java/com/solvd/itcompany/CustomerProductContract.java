/*
 * %W% %E% Pavel Shyrkavets
 *
 * Copyright (c) 2011-2024 Solvd, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Solvd,
 * Inc. ("Confidential Information.") You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Solvd.
 *
 * SOLVD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SOLVD SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.solvd.itcompany;

import com.solvd.itcompany.exceptions.DateTimeException;
import com.solvd.itcompany.exceptions.ZeroOrTooManyCharactersException;
import com.solvd.itcompany.exceptions.ZeroOrTooManyUSDException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class CustomerProductContract extends Contract {
    private final static int MASK = 90;
    private final static BigDecimal MAX_PRODUCT_PRICE_IN_USD =
            new BigDecimal("100000000.00");
    private final static String PRODUCT_PRICE_MESSAGE =
            "The product price should not be less than or equal to 0 "
            + "or exceed " + MAX_PRODUCT_PRICE_IN_USD + " USD.";
    private final static Logger LOGGER =
            LogManager.getLogger(CustomerProductContract.class);

    public BigDecimal productPriceInUSD = null;

    public CustomerProductContract() {}

    public CustomerProductContract(String contractType, LocalDate startDateOfContract,
                                   BigDecimal productPriceInUSD) throws ZeroOrTooManyUSDException,
                                   ZeroOrTooManyCharactersException, DateTimeException {
        super(contractType, startDateOfContract);

        if (productPriceInUSD.compareTo(new BigDecimal("0.00")) == 0
                || productPriceInUSD.compareTo(MAX_PRODUCT_PRICE_IN_USD) > 0) {
            LOGGER.error(PRODUCT_PRICE_MESSAGE);
            throw new ZeroOrTooManyUSDException(PRODUCT_PRICE_MESSAGE);
        }

        this.productPriceInUSD = productPriceInUSD;
    }

    public BigDecimal getProductPriceInUSD() {
        return productPriceInUSD;
    }

    public void setTotalProductPriceInUSD(BigDecimal productPriceInUSD)
            throws ZeroOrTooManyUSDException {
        if (productPriceInUSD.compareTo(new BigDecimal("0.00")) == 0
                || productPriceInUSD.compareTo(MAX_PRODUCT_PRICE_IN_USD) > 0) {
            LOGGER.error(PRODUCT_PRICE_MESSAGE);
            throw new ZeroOrTooManyUSDException(PRODUCT_PRICE_MESSAGE);
        }

        this.productPriceInUSD = productPriceInUSD;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (hashCode() != object.hashCode()) {
            return false;
        }

        CustomerProductContract that = (CustomerProductContract) object;
        return Objects.equals(getContractTypeName(), that.getContractTypeName())
                && Objects.equals(getStartDateOfContract(), that.getStartDateOfContract())
                && Objects.equals(getProductPriceInUSD(), that.getProductPriceInUSD());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContractTypeName(), getStartDateOfContract(),
                            getProductPriceInUSD(), MASK);
    }

    @Override
    public String toString() {
        return "EmployeeJobContract{"
                + "startDateOfContract='" + getStartDateOfContract() + '\''
                + "productPriceInUSD='" + getProductPriceInUSD() + '\''
                + '}';
    }
}
