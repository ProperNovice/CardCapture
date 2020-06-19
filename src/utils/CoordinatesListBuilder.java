package utils;

import controller.Credentials;

public class CoordinatesListBuilder {

	private double x = 0, y = 0, width = 0, height = 0, gapX = Credentials.INSTANCE.dGapBetweenComponents.x,
			gapY = Credentials.INSTANCE.dGapBetweenComponents.y;
	private int objectsPerRow = -1;
	private RearrangeTypeEnum rearrangeTypeEnum = RearrangeTypeEnum.LINEAR;
	private DirectionEnum directionEnumHorizontal = DirectionEnum.RIGHT, directionEnumVertical = DirectionEnum.DOWN;

	public CoordinatesListBuilder() {

	}

	public CoordinatesListBuilder x(double x) {
		this.x = x;
		return this;
	}

	public CoordinatesListBuilder y(double y) {
		this.y = y;
		return this;
	}

	public CoordinatesListBuilder coordinatesNumbersPair(NumbersPair numbersPair) {

		this.x = numbersPair.x;
		this.y = numbersPair.y;
		return this;

	}

	public CoordinatesListBuilder width(double width) {
		this.width = width;
		return this;
	}

	public CoordinatesListBuilder height(double height) {
		this.height = height;
		return this;
	}

	public CoordinatesListBuilder dimensionsNumbersPair(NumbersPair numbersPair) {

		this.width = numbersPair.x;
		this.height = numbersPair.y;
		return this;

	}

	public CoordinatesListBuilder gapX(double gapX) {
		this.gapX = gapX;
		return this;
	}

	public CoordinatesListBuilder gapY(double gapY) {
		this.gapY = gapY;
		return this;
	}

	public CoordinatesListBuilder gapNumbersPair(NumbersPair numbersPair) {
		this.gapX = numbersPair.x;
		this.gapY = numbersPair.y;
		return this;
	}

	public CoordinatesListBuilder objectsPerRow(int objectsPerRow) {
		this.objectsPerRow = objectsPerRow;
		return this;
	}

	public CoordinatesListBuilder rearrangeTypeEnum(RearrangeTypeEnum rearrangeTypeEnum) {
		this.rearrangeTypeEnum = rearrangeTypeEnum;
		return this;
	}

	public CoordinatesListBuilder directionEnumHorizontal(DirectionEnum directionEnumHorizontal) {
		this.directionEnumHorizontal = directionEnumHorizontal;
		return this;
	}

	public CoordinatesListBuilder directionEnumVertical(DirectionEnum directionEnumVertical) {
		this.directionEnumVertical = directionEnumVertical;
		return this;
	}

	public CoordinatesList build() {
		return new CoordinatesList(this.x, this.y, width, this.height, this.gapX, this.gapY, this.objectsPerRow,
				this.rearrangeTypeEnum, this.directionEnumHorizontal, this.directionEnumVertical);
	}

}
