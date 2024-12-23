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

import com.solvd.itcompany.enums.SoftwareType;
import com.solvd.itcompany.exceptions.ZeroOrTooManyCharactersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Software {
    private final static int MAX_SOFTWARE_NAME_LENGTH = 100;
    private final static String SOFTWARE_NAME_MESSAGE =
            "The software name length should not be equal to 0 "
            + "or exceed " + MAX_SOFTWARE_NAME_LENGTH + " characters.";
    private final static Logger LOGGER = LogManager.getLogger(Software.class);

    private SoftwareType type = null;
    private String name;

    public Software() {}

    public Software(SoftwareType type, String name)
            throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_SOFTWARE_NAME_LENGTH) {
            LOGGER.error(SOFTWARE_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(SOFTWARE_NAME_MESSAGE);
        }

        this.type = type;
        this.name = name;
    }

    public SoftwareType getType() {
        return type;
    }

    public void setType(SoftwareType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_SOFTWARE_NAME_LENGTH) {
            LOGGER.error(SOFTWARE_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(SOFTWARE_NAME_MESSAGE);
        }

        this.name = name;
    }
}
