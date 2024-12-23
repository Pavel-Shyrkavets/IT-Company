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
package com.solvd.itcompany.interfaces;

import com.solvd.itcompany.Contract;
import com.solvd.itcompany.Employee;
import com.solvd.itcompany.Person;
import com.solvd.itcompany.exceptions.ZeroOrTooManyUSDException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public interface ISign {
    Logger LOGGER = LogManager.getLogger(ISign.class);

    static void signPayRaise(Employee employee, BigDecimal additionToSalary)
            throws ZeroOrTooManyUSDException {
        if (additionToSalary.compareTo(new BigDecimal("0.00")) == 0
                || employee.getSalaryInUSDPerMonth().add(additionToSalary)
                .compareTo(Employee.getMaxSalaryInUsdPerMonth()) > 0) {
            LOGGER.info(Employee.getSalaryMessage());
            System.exit(1);
        }

        BigDecimal employeeSalary = employee.getSalaryInUSDPerMonth();
        employee.setSalaryInUSDPerMonth(employeeSalary.add(additionToSalary));
    }

    void terminateContract(Person person, Contract contract);

    default String signNewContract(Person person, Contract contract) {
        return "The " + contract.getContractTypeName() + " with "
                + person.getFirstName() + " " + person.getLastName() + " is signed.";
    }
}
