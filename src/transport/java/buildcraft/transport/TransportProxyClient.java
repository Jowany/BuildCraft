/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL. Please check the contents
 * of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt */
package buildcraft.transport;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import buildcraft.transport.item.ItemPipe;
import buildcraft.transport.render.PipeRendererTESR;
import buildcraft.transport.render.TileEntityPickupFX;

public class TransportProxyClient extends TransportProxy {

    // public static final PipeItemRenderer pipeItemRenderer = new PipeItemRenderer();
    // public static final PipeRendererWorld pipeWorldRenderer = new PipeRendererWorld();
    // public static final FacadeItemRenderer facadeItemRenderer = new FacadeItemRenderer();
    // public static final PlugItemRenderer plugItemRenderer = new PlugItemRenderer();
    // public static final GateItemRenderer gateItemRenderer = new GateItemRenderer();

    @Override
    public void registerTileEntities() {
        super.registerTileEntities();
        PipeRendererTESR rp = new PipeRendererTESR();
        ClientRegistry.bindTileEntitySpecialRenderer(TileGenericPipe.class, rp);
    }

    @Override
    public void obsidianPipePickup(World world, EntityItem item, TileEntity tile) {
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(new TileEntityPickupFX(world, item, tile));
    }

    @Override
    public void registerRenderers() {
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsWood, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsCobblestone, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsStone, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsQuartz, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsIron, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsGold, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsDiamond, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsObsidian, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsEmerald, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsLapis, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsDaizuli, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsEmzuli, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsStripes, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsClay, pipeItemRenderer);
        //
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsWood, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsCobblestone, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsStone, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsQuartz, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsIron, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsGold, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsDiamond, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsEmerald, pipeItemRenderer);
        //
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerWood, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerCobblestone, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerStone, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerQuartz, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerIron, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerGold, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerDiamond, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerEmerald, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeStructureCobblestone, pipeItemRenderer);
        //
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsVoid, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsVoid, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsSandstone, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeFluidsSandstone, pipeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipePowerSandstone, pipeItemRenderer);
        //
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.facadeItem, facadeItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.plugItem, plugItemRenderer);
        // MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeGate, gateItemRenderer);
    }

    @Override
    public void setIconProviderFromPipe(ItemPipe item, Pipe<?> dummyPipe) {
        item.setPipesIcons(dummyPipe.getIconProvider());
    }
}