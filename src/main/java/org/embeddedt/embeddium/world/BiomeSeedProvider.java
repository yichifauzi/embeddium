package org.embeddedt.embeddium.world;

import net.minecraft.client.multiplayer.ClientLevel;

public interface BiomeSeedProvider {
    static long getBiomeSeed(ClientLevel world) {
        return ((BiomeSeedProvider) world).sodium$getBiomeSeed();
    }

    long sodium$getBiomeSeed();
}