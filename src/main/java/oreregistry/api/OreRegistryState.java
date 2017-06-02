/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api;


public enum OreRegistryState {
    /**
     * This state is active if in the pre init phase of fml.
     */
    ACTIVE,
    /**
     * This state is always active after the pre init phase of fml except the mod chooses currently his products or its currently synchronises with the server.
     */
    INACTIVE,
    /**
     * This state is active if the client currently synchronises with the server.
     */
    SYNCHRONIZE,
    /**
     * This state is active if the mod currently chooses the products.
     */
    CHOOSE

}
