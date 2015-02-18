/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.it;

import org.junit.BeforeClass;
import org.junit.Test;

import io.sightly.tck.http.Client;
import io.sightly.tck.html.HTMLExtractor;

import static org.junit.Assert.assertEquals;

public class SlingSpecificsSightlyIT {

    private static Client client;
    private static String launchpadURL;
    private static final String SLING_USE = "/sightly/use.html";
    private static final String SLING_RESOURCE = "/sightly/resource.html";

    @BeforeClass
    public static void init() {
        client = new Client();
        launchpadURL = System.getProperty("launchpad.http.server.url");
    }

    @Test
    public void testSlingModelsUseAPI() {
        String url = launchpadURL + SLING_USE;
        String pageContent = client.getStringContent(url, 200);
        assertEquals("SUCCESS", HTMLExtractor.innerHTML(url, pageContent, "#reqmodel"));
        assertEquals("SUCCESS", HTMLExtractor.innerHTML(url, pageContent, "#resmodel"));
        
    }
    
    @Test
    public void testAdaptablesUseAPI() {
        String url = launchpadURL + SLING_USE;
        String pageContent = client.getStringContent(url, 200);
        assertEquals("SUCCESS", HTMLExtractor.innerHTML(url, pageContent, "#resadapt"));
        assertEquals("SUCCESS", HTMLExtractor.innerHTML(url, pageContent, "#reqadapt"));
    }

    @Test
    public void testDataSlyResourceArraySelectors() {
        String url = launchpadURL + SLING_RESOURCE;
        String pageContent = client.getStringContent(url, 200);
        assertEquals("selectors: a.b", HTMLExtractor.innerHTML(url, pageContent, "#selectors span.selectors"));
        assertEquals("selectors: a.b", HTMLExtractor.innerHTML(url, pageContent, "#selectors-remove-c span.selectors"));
        assertEquals("selectors: a.c", HTMLExtractor.innerHTML(url, pageContent, "#removeselectors-remove-b span.selectors"));
        assertEquals("selectors: a.b.c", HTMLExtractor.innerHTML(url, pageContent, "#addselectors span.selectors"));
    }

}