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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public abstract class Contract {
    private final static int MAX_CONTRACT_TYPE_NAME_LENGTH = 50;
    private final static String CONTRACT_MESSAGE =
            "The contract type name length should not be equal to 0 "
            + "or exceed " + MAX_CONTRACT_TYPE_NAME_LENGTH + " characters.";
    private final static String START_OF_CONTRACT_MESSAGE =
            "The start date of the contract should not be earlier "
            + "than the current date.";
    private final static Logger LOGGER = LogManager.getLogger(Contract.class);

    private String contractTypeName = "";
    private LocalDate startDateOfContract = null;

    public Contract() {}

    public Contract(String contractTypeName, LocalDate startDateOfContract)
            throws ZeroOrTooManyCharactersException, DateTimeException {
        if (contractTypeName.isEmpty()
                || contractTypeName.length() > MAX_CONTRACT_TYPE_NAME_LENGTH) {
            LOGGER.error(CONTRACT_MESSAGE);
            throw new ZeroOrTooManyCharactersException(CONTRACT_MESSAGE);
        }

        if (startDateOfContract.isBefore(LocalDate.now())) {
            LOGGER.error(START_OF_CONTRACT_MESSAGE);
            throw new DateTimeException(START_OF_CONTRACT_MESSAGE);
        }

        this.contractTypeName = contractTypeName;
        this.startDateOfContract = startDateOfContract;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName)
            throws ZeroOrTooManyCharactersException {
        if (contractTypeName.isEmpty()
                || contractTypeName.length() > MAX_CONTRACT_TYPE_NAME_LENGTH) {
            LOGGER.error(CONTRACT_MESSAGE);
            throw new ZeroOrTooManyCharactersException(CONTRACT_MESSAGE);
        }

        this.contractTypeName = contractTypeName;
    }

    public LocalDate getStartDateOfContract() {
        return startDateOfContract;
    }

    public void setStartDateOfContract(LocalDate startDateOfContract)
            throws DateTimeException {
        if (startDateOfContract.isBefore(LocalDate.now())) {
            LOGGER.error(START_OF_CONTRACT_MESSAGE);
            throw new DateTimeException(START_OF_CONTRACT_MESSAGE);
        }

        this.startDateOfContract = startDateOfContract;
    }
}
