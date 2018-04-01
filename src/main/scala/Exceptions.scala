/**
 * Created by bhavinprajapati on 15-12-23.
 */
package com.garner

class IncorrectFileFormatException(message: String = null, cause: Throwable = null) extends RuntimeException(message, cause)
class IncorrectValueTypeException(message: String = null, cause: Throwable = null) extends RuntimeException(message, cause)
class MissingValueException(message: String = null, cause: Throwable = null) extends RuntimeException(message, cause)
class DuplicateIDException(message: String = null, cause: Throwable = null) extends RuntimeException(message, cause)
class UnknownWeightTypeException(message: String = null, cause: Throwable = null) extends RuntimeException(message, cause)
class NegativeWeightException(message: String = null, cause: Throwable = null) extends RuntimeException(message, cause)
