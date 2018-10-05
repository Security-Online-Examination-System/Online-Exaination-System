/**
 * Dynamsoft JavaScript Library
 * @product Dynamsoft Camera SDK
 * @website http://www.dynamsoft.com
 *
 * @preserve Copyright 2017, Dynamsoft Corporation
 * @author Dynamsoft
 *
 * @version 6.1
 *
 * @fileoverview Dynamsoft JavaScript Library for Basic Initiation of Dynamsoft Camera SDK
 * More info on DCS: http://www.dynamsoft.com/products/dynamsoft-webcam-sdk.aspx
 */

var dynamsoft = dynamsoft || { dcsEnv: {} };
dynamsoft.dcsEnv = dynamsoft.dcsEnv || {};

// Sets whether it's trial.
dynamsoft.dcsEnv.bTrial = true;

//////////////////////////////////////////////////////////////////////////////

//  WARNING:  The productKey in this file is protected by copyright law    //

//  and international treaty provisions. Unauthorized reproduction or       //

//  distribution of this  productKey, or any portion of it, may result in severe   //

//  criminal and civil penalties, and will be prosecuted to the maximum     //

//  extent possible under the law.  Further, you may not reverse engineer,  //

//  decompile, disassemble, or modify the  productKey .                             //

//////////////////////////////////////////////////////////////////////////////
dynamsoft.dcsEnv.productKey = 't0068NQAAAGBdFEo1uVIKqR4yZWXWZTTSlneguCKtGusXM+iOnvW/ERaIIzMWzdYLa/sjFqksvV5Bdvvk1OxH4pIQU1YmMss=';

/// Specifies the time-out period for the session, in seconds. The default value is 1200. 0 means session will not time out.
dynamsoft.dcsEnv.sessionTimeout = 1200;

/// optional
dynamsoft.dcsEnv.resourcesPath = 'https://demo.dynamsoft.com/DCS/DCSResources';
