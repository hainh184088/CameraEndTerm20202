package Object;

import Point.Point;

public class Surface {

    public static int checkPointOnSurface(Point point, double[] surface){
        if((point.getX() * surface[0] + point.getY() * surface[1] + point.getZ() * surface[2] + surface[3]) == 0) {
            System.out.println("Point is on surface");
            return 1;
        }
        else return 0;

    }

    public static int checkSurfaceIntersection(double[] surface1, double[] surface2) {
        if (
                ((surface1[0] / surface2[0] != surface1[1] / surface2[1]) && surface2[0] != 0 && surface2[1] != 0) ||
                        ((surface1[1] / surface2[1] != surface1[2] / surface2[2]) && surface2[1] != 0 && surface2[2] != 0) ||
                        ((surface1[0] / surface2[0] != surface1[2] / surface2[2]) && surface2[0] != 0 && surface2[2] != 0)
        ) {
            System.out.println("The two surfaces intersected");
            return 1;
        } else return 0;
    }
}
