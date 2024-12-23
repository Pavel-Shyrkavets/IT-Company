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

import java.util.Objects;

public class WebApplication extends Software {
    private final static int MASK = 10;
    private final static int MAX_URL_LENGTH = 2048;
    private final static String URL_LENGTH_MESSAGE =
            "The URL length should not be equal to 0 "
            + "or exceed " + MAX_URL_LENGTH + " characters.";
    private final static Logger LOGGER = LogManager.getLogger(WebApplication.class);

    private String URL = "";

    public WebApplication() {}

    public WebApplication(SoftwareType type, String name, String URL)
                          throws ZeroOrTooManyCharactersException {
        super(type, name);

        if (URL.isEmpty() || URL.length() > MAX_URL_LENGTH) {
            LOGGER.error(URL_LENGTH_MESSAGE);
            throw new ZeroOrTooManyCharactersException(URL_LENGTH_MESSAGE);
        }

        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) throws ZeroOrTooManyCharactersException {
        if (URL.isEmpty() || URL.length() > MAX_URL_LENGTH) {
            LOGGER.error(URL_LENGTH_MESSAGE);
            throw new ZeroOrTooManyCharactersException(URL_LENGTH_MESSAGE);
        }

        this.URL = URL;
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

        WebApplication that = (WebApplication) object;
        return Objects.equals(getURL(), that.getURL())
                && Objects.equals(getType(), that.getType())
                && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getURL(), getType(), getName(), MASK);
    }

    @Override
    public String toString() {
        return "WebApplication{"
                + "URL='" + getURL() + '\''
                + ", SoftwareType='" + getType() + '\''
                + ", name='" + getName() + '\''
                + '}';
    }
}
