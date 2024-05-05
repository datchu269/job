package com.example.validateauto.validate;

 public interface AppConfiguration extends Configuration {

   /**
    * Add elements to configuration
    * @param e elements identifier
    * @param value array of elements
    * @return instance of configuration
    */
   AppConfiguration add(Ordinal e, Configuration... value);
//
//   /**
//    *
//    * @param e element identifier
//    * @param value parameter value
//    * @return instance of configuration
//    */
   AppConfiguration add(Ordinal e, Object value);
 }
