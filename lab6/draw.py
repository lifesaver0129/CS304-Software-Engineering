'''
Name: Yuxing Hu
StudentNo: 11510225
Creation data: result.txt
'''

import matplotlib.patches as patches
import matplotlib.pyplot as plt
import numpy as np


# read the data file
def main():
    code = open("fault-localization/src/triangle/src/main/java/triangle/Triangle.java", 'r')
    index = 1
    code_list = []
    s = code.readline()
    while s:
        lines = str(index)
        lines += ":   "
        lines += s
        code_list.append(lines[:-1])
        index += 1
        s = code.readline()
    # data = np.loadtxt('fault-localization/tarantula_result.txt')
    # data = np.loadtxt('fault-localization/kulczynski_result.txt')
    # data = np.loadtxt('fault-localization/dstar2_result.txt')
    data = np.loadtxt('fault-localization/wong2_result.txt')
    color = convert_color(data)
    bright = convert_bright(data)
    bright2 = convert_bright_2(data)
    draw(code_list, color, bright, 1)
    draw(code_list, color, bright2, 2)
    # for item in code_list:
    #     print(item)
    # with open('fault-localization/result.txt', 'r') as f:
    #     try:
    #         pass
    #         draw(f.readline())
    #     except IOError as e:
    #         pass
    #     finally:
    #         pass


def convert_color(data):
    color = []
    for item in data[:, 1]:
        if item == -1.0:
            color.append(0.0)
        else:
            color.append(1 - item * 0.5)
    return color


def convert_bright(data):
    color = []
    for item in data[:, 2]:
        if item == -1.0:
            color.append(0.0)

        else:
            color.append(1)
    return color


def convert_bright_2(data):
    color = []
    for item in data[:, 2]:
        if item == -1.0:
            color.append(0.0)
        elif item * 2 < 1:
            color.append(item * 2)
        else:
            color.append(1)
    return color


# def get_jet():
#     colormap_int = np.zeros((256, 3), np.uint8)
#     colormap_float = np.zeros((256, 3), np.float)
#
#     for i in range(0, 256, 1):
#         colormap_float[i, 0] = plt.cm.jet(i)[0]
#         colormap_float[i, 1] = plt.cm.jet(i)[1]
#         colormap_float[i, 2] = plt.cm.jet(i)[2]
#
#         colormap_int[i, 0] = np.int_(np.round(plt.cm.jet(i)[0] * 255.0))
#         colormap_int[i, 1] = np.int_(np.round(plt.cm.jet(i)[1] * 255.0))
#         colormap_int[i, 2] = np.int_(np.round(plt.cm.jet(i)[2] * 255.0))
#
#     np.savetxt("jet_float.txt", colormap_float, fmt="%f", delimiter=' ', newline='\n')
#     np.savetxt("jet_int.txt", colormap_int, fmt="%d", delimiter=' ', newline='\n')
#     print(colormap_int)
#     return


def draw(code_list, color, bright, tag):
    # replace following code to draw
    plt.axis('off')
    figx = plt.figure(figsize=(8, 9))
    ax = figx.add_subplot(111, aspect='equal')
    ax.axis('off')
    plt.ylim(0, 2)
    plt.xlim(0, 2)
    for item in range(len(code_list)):
        plt.text(0, 2 - item * 0.06, code_list[item])
    for p in [
        patches.Rectangle(
            (0.08, 2 - item * 0.06), 1.2, 0.035,
            facecolor=plt.get_cmap('jet')(color[item]), alpha=bright[item]
        ) for item in range(len(color))
    ]:
        ax.add_patch(p)
    figx.savefig("result_%d.png" % tag, dpi=500)
    figx.show()


if __name__ == '__main__':
    main()
