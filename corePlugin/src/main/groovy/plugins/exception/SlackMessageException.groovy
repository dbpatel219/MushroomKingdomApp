package plugins.exception

import org.grails.core.exceptions.GrailsException

class SlackMessageException extends GrailsException {

    public SlackMessageException() {
        super()
    }

    SlackMessageException(String msg, obj = null) {
        super("${msg}${obj ? ' -> ' + obj.toString() : ''}")
    }
	
}