__config() -> {
    'scope' -> 'global'
};

// 1. LISTA CON LOS IDs COMPLETOS (porque str(block) e item:0 devuelven "minecraft:xxx")
rare_targets = {
    'diamond_block',
    'beacon',
    'netherite_block',
    'netherite_ingot',
    'netherite_scrap',
    'diamond',
    'netherite_helmet',
    'netherite_chestplate',
    'netherite_leggings',
    'netherite_boots'
};

// 2. ROMPER BLOQUE
// str(block) es la forma documentada de obtener "minecraft:xxx" de un valor de bloque
__on_player_breaks_block(player, block) -> (
    if (rare_targets ~ block != null,
    task(_(outer(player), outer(block)) -> (
        run(str('say :warning: %s break a block %s at %s in %s', player, block, pos(block), current_dimension() ));
        ));
    );
);

// 3. COLOCAR BLOQUE
__on_player_places_block(player, item_tuple, hand, block) -> (
    if (rare_targets ~ block != null,
    task(_(outer(player), outer(block)) -> (
        run(str('say :warning: %s placed %s at %s in %s', player, block, pos(block), current_dimension() ));
        ));
    );
);

// 4. RECOGER ITEM
// item:0 ya es un string tipo "minecraft:diamond", así que coincide directo con la lista
__on_player_picks_up_item(player, item) -> (
    if (rare_targets ~ item:0 != null,
    task(_(outer(player), outer(item)) -> (
        run(str('say :warning: %s picked up %s (qty: %s)', player, item:0, item:1) );
        ));
    );
);