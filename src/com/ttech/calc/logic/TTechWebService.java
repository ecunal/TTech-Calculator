
package com.ttech.calc.logic;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "TTechWebService", targetNamespace = "http://webservice.ttech.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TTechWebService {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "multiply", targetNamespace = "http://webservice.ttech.com/", className = "calculator.Multiply")
    @ResponseWrapper(localName = "multiplyResponse", targetNamespace = "http://webservice.ttech.com/", className = "calculator.MultiplyResponse")
    public Double multiply(
        @WebParam(name = "arg0", targetNamespace = "")
        Double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "divide", targetNamespace = "http://webservice.ttech.com/", className = "calculator.Divide")
    @ResponseWrapper(localName = "divideResponse", targetNamespace = "http://webservice.ttech.com/", className = "calculator.DivideResponse")
    public Double divide(
        @WebParam(name = "arg0", targetNamespace = "")
        Double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://webservice.ttech.com/", className = "calculator.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://webservice.ttech.com/", className = "calculator.AddResponse")
    public Double add(
        @WebParam(name = "arg0", targetNamespace = "")
        Double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "subtract", targetNamespace = "http://webservice.ttech.com/", className = "calculator.Subtract")
    @ResponseWrapper(localName = "subtractResponse", targetNamespace = "http://webservice.ttech.com/", className = "calculator.SubtractResponse")
    public Double subtract(
        @WebParam(name = "arg0", targetNamespace = "")
        Double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Double arg1);

}
