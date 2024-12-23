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
package com.solvd.itcompany.enums;

public enum ContractType {
    B2B_CONTRACT ("B2B contract"),
    EMPLOYEE_JOB_CONTRACT ("job contract"),
    CUSTOMER_PRODUCT_CONTRACT ("product contract");

    private final String contractType;

    ContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractType() {
        return contractType;
    }

    @Override
    public String toString() {
        return contractType;
    }
}
