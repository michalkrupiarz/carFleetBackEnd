package bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExceptionMessage {
	String exceptionMessage;
	String exceptionCode;
	public ExceptionMessage(String exceptionMessage, String exceptionCode) {
		super();
		this.exceptionMessage = exceptionMessage;
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
	

}
