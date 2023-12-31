package ghana7.cheesemaking.rendering;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.tileentity.CurdingTubTileEntity;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;

import static net.fabricmc.api.EnvType.CLIENT;
import static net.minecraft.world.item.ItemDisplayContext.FIXED;

@Environment(CLIENT)
public class CurdingTubTileEntityRenderer implements BlockEntityRenderer<CurdingTubTileEntity> {
    public CurdingTubTileEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(CurdingTubTileEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if(tileEntityIn.getMilk() > 0) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.0625f, 0, 0.0625f);
            matrixStackIn.translate(0, 0.0625f * 1.5f * (tileEntityIn.getMilk() / 1000), 0);
            matrixStackIn.scale(0.875f, 0.0625f, 0.875f);

            var blockRenderer = Minecraft.getInstance().getBlockRenderer();
            var state = CheesemakingMod.MILK_BLOCK.get().defaultBlockState();
            blockRenderer.renderSingleBlock(state, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
            matrixStackIn.popPose();
        }
        int numRennets = tileEntityIn.itemHandler.getItem(0).getCount();
        ItemStack rennetItemStack = tileEntityIn.itemHandler.getItem(0);
        int numCurds = tileEntityIn.itemHandler.getItem(1).getCount();
        ItemStack curdItemStack = tileEntityIn.itemHandler.getItem(1);
        matrixStackIn.pushPose();
        Lighting.setupForFlatItems();
        if(numRennets > 0) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.25, 0.375, 0.25);
            matrixStackIn.scale(0.5f,0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(rennetItemStack, FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, null, 0);
            matrixStackIn.popPose();
        }
        if(numRennets > 1) {

            matrixStackIn.pushPose();
            matrixStackIn.translate(0.75, 0.375, 0.25);
            matrixStackIn.scale(0.5f,0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(rennetItemStack, FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, null, 0);
            matrixStackIn.popPose();
        }
        if(numRennets > 2) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.75, 0.375, 0.75);
            matrixStackIn.scale(0.5f,0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(rennetItemStack, FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, null, 0);
            matrixStackIn.popPose();
        }
        if(numRennets > 3) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.25, 0.375, 0.75);
            matrixStackIn.scale(0.5f,0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(rennetItemStack, FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, null, 0);
            matrixStackIn.popPose();
        }
        if(numCurds > 0) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5, 0.0625 + 0.0625f * 1.5f * (tileEntityIn.getMilk() / 1000f), 0.5);
            matrixStackIn.mulPose(Axis.XP.rotationDegrees(90));
            matrixStackIn.scale(0.5f,0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(curdItemStack, FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, null, 0);
            matrixStackIn.popPose();
        }
        Lighting.setupFor3DItems();
        matrixStackIn.popPose();
    }
}
