 /*
 * Copyright 2015 Health and Social Care Information Centre
 Solution Assurance damian.murphy@hscic.gov.uk

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License..
 */
package uk.nhs.digital.mait.spinetools.spine.connection;

/**
 *  Enable reporting of EbXML events.
 * 
 * @author  Damian Murphy damian.murphy@hscic.gov.uk
 */
public interface EbXmlEventListener {
    
    /**
     * Called from within the Spine Tools ebXML handling code when a reliable message is 
     * "completed". When called from removeRequest() no "ack" will be included (because
     * removeRequest() is called when there is no ack, such as on expiry), otherwise a string
     * containing the ack or message error will be provided.
     * 
     * @param msgId ID of message being "completed"
     * @param comment Some information as to why
     * @param ack For acks/nacks, the text of the response.
     */
    public void eventNotification(String msgId, String comment, String ack);
}
